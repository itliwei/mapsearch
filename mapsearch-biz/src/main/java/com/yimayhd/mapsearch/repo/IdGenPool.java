package com.yimayhd.mapsearch.repo;

import com.yimayhd.idgen.IDGenService;
import com.yimayhd.idgen.client.MemIDPool;

/**
 * Pool that use local memory to cache IDs.
 * It will automatically fetch new IDs from global ID generator through dubbo service "com.pajk.idgen.IDGenService"
 *
 * CAUTION:
 * This id pool will PERMANENTLY LOSE IDs cached in memory when JVM restarts.
 *
 * @author Haomin Liu
 */
public class IdGenPool extends MemIDPool {

    public IdGenPool(String configDomain, String configKey, int allocCount, IDGenService generator) {
        super(configDomain, configKey, allocCount, generator);
    }

    public long getNewId() {
        String borrowId=borrow();
        consume(borrowId);
        return Long.valueOf(borrowId);
    }
}

