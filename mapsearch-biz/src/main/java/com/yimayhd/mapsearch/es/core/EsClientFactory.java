package com.yimayhd.mapsearch.es.core;

import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * EsClient
 *
 * @author lilin
 * @date 16/9/27
 */

@Component
public class EsClientFactory {

    private final static Logger logger = LoggerFactory.getLogger(EsClientFactory.class);

    @Value("${es.cluster.name}")
    private String clusterName;

    @Value("${es.nodes}")
    private String esNodes;

    private Client client;

    public Client getClient() {
        return client;
    }

    @PostConstruct
    public void init() {
        logger.info("es client begin init");
        client = getTransportClient();
        mappingForIndexs();
        logger.info("es client init success");
    }


    public void mappingForIndexs(){
        Path mappingPath = Paths.get(EsClientFactory.class.getResource("/").getPath() + "/mapping");
        try {
            Files.walkFileTree(mappingPath, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                    String mappingFile = file.getFileName().toString();
                    if (mappingFile.contains("mapping")) {
                        String[] mappingInfo = mappingFile.split("-");
                        if (mappingInfo.length > 2) {
                            if (file.toFile().canRead()) {
                                logger.info("begin set index type mapping {}", mappingFile);
                                try {
                                    String json = new String(Files.readAllBytes(file));
                                    setIndexTypeMapping(mappingInfo[0], mappingInfo[1],json);
                                } catch (IOException e) {
                                    logger.error("error when read file {}, exception: {}", new Object[]{mappingFile, e});
                                }
                            }
                        } else {
                            logger.warn("file {} is not valid mapping file name!", mappingFile);
                        }
                    } else {
                        logger.warn("file {} is not mapping file!", mappingFile);
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (Exception e) {
            logger.error("{}", e);
        }
    }


    /**
     * 设置typ mapping 的前提条件是index 存在
     * @param _index
     * @param _type
     * @param mapping
     * @return
     */
    public boolean setIndexTypeMapping(String _index, String _type, String mapping) {

        boolean existIndex = client.admin().indices().prepareExists(_index).get().isExists();
        if(!existIndex){
            boolean createSuc = client.admin().indices().prepareCreate(_index).get().isAcknowledged();
            if(!createSuc){
                return false;
            }
        }
        return client.admin().indices().preparePutMapping(_index).setType(_type).setSource(mapping).get().isAcknowledged();
    }

    public Client getTransportClient() {
        Settings settings = Settings.settingsBuilder()
                .put("cluster.name", clusterName)
                .put("client.transport.sniff", true)
                .build();
        try {
            if (StringUtils.isNotBlank(esNodes)) {
                String[] nodes = esNodes.split(",");
                if (nodes.length == 0) {
                    logger.error("es.nodes config error");
                    return null;
                }
                TransportAddress[] transportAddresses = new TransportAddress[nodes.length];
                for (int i = 0;i<nodes.length;i++){
                    String[] ipPort = nodes[i].split(":");
                    if (ipPort.length == 2) {
                        transportAddresses[i] = new InetSocketTransportAddress(InetAddress.getByName(ipPort[0]), Integer.parseInt(ipPort[1]));
                    }
                }
                return TransportClient.builder().settings(settings).build().addTransportAddresses(transportAddresses);
            } else {
                logger.error("es.nodes config error");
                return null;
            }

        } catch (UnknownHostException e) {
            logger.error("es client init error", e);
            return null;
        }
    }
}
