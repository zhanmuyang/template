package com.bytebpm.base.service.cache;

import java.util.List;

public interface CacheService {

    /**
     * 将对象保存到redis中（转换为JSON后保存）
     * 
     * @param key
     * @param bean
     * @return
     */
    boolean save2Cache(String key, Object bean, Integer seconds);

    /**
     * 根据key取得redis中保存的对象
     * @param <T>
     * 
     * @param key
     * @return
     */
    <T> T getFromCache(String key, Class<T> clazz);

    /**
     * 根据key直接取得json数据
     * 
     * @param key
     * @return
     */
    String getFromCache(String key);

    /**
     * 根据key删除redis中缓存的数据
     * 
     * @param key
     * @return
     */
    boolean deleteCache(String key);

	boolean save2Cache(String key, String value, Integer seconds);

	boolean deleteByPattern(String pattern);

	List<String> keysByPattern(String pattern);
}
