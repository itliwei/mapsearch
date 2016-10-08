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
import java.net.InetAddress;
import java.net.UnknownHostException;

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
        logger.info("es client init success");
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
