package com.thebigblue.web.utils;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheStats;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public class GuavaUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(GuavaUtil.class);

    private static Cache<String, Object> loadingCache = CacheBuilder.newBuilder()
            /*设置缓存容器的初始容量大小为10*/
            .initialCapacity(100)
            /*设置缓存容器的最大容量大小为100*/
            .maximumSize(100)
            /*设置记录缓存命中率*/
            .recordStats()
            /*设置并发级别为8*/
            .concurrencyLevel(8)
            /*设置过期时间为1天*/
            .expireAfterAccess(1, TimeUnit.DAYS).build();

    public static void put(String key, Object value) {
        loadingCache.put(key, value);
    }

    public static Object get(String key) {
        Object dataObj = loadingCache.getIfPresent(key);
        LOGGER.info("[ " + key + " ] " + (dataObj == null ? "未" : "") + "命中缓存");
        return dataObj;
    }

    public static Map<String, Object> getAll() {
        return loadingCache.asMap();
    }

    public static void remove(String key) {
        loadingCache.invalidate(key);
    }

    public static void clean() {
        loadingCache.invalidateAll();
    }

    public static CacheStats getStat() {
        return loadingCache.stats();
    }

    public static void main(String[] args) throws Exception {
        GuavaUtil test01 = new GuavaUtil();
        test01.put("hello", "world");
        System.out.println(test01.get("hello"));
        Thread.sleep(2001);
        String str = (String) test01.get("hello");
        System.out.println(str);
        for (int i = 0; i < 100; i++) {
            System.out.println(System.currentTimeMillis());
            Thread.sleep(1000);
            System.out.println(System.currentTimeMillis());
        }
    }
}
