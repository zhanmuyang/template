package com.bytebpm.base.service.cache;

import com.bytebpm.util.SerializeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ShardedJedis;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;

@Service("cacheService")
public class CacheServiceImpl implements CacheService {

    private static final Logger logger = LoggerFactory.getLogger(RedisDataSourceServiceImpl.class);

    @Inject
    private RedisDataSourceService redisDataSourceService;

    @Override
    public boolean save2Cache(String key, Object bean, Integer seconds) {
        ShardedJedis shardedJedis = redisDataSourceService.getRedisClient();
        if (shardedJedis == null) {
            return false;
        }
        try {
            byte[] value = SerializeUtil.serialize(bean);
            shardedJedis.set(key.getBytes(), value);
            if (seconds != null && seconds > 0) {
            	shardedJedis.expire(key, seconds);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        } finally {
            redisDataSourceService.returnResource(shardedJedis);
        }
        return true;
    }
    
    @Override
    public boolean save2Cache(String key, String value, Integer seconds) {
        ShardedJedis shardedJedis = redisDataSourceService.getRedisClient();
        if (shardedJedis == null) {
            return false;
        }
        try {
        	
            shardedJedis.set(key, value);
            if (seconds != null && seconds > 0) {
            	shardedJedis.expire(key, seconds);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        } finally {
            redisDataSourceService.returnResource(shardedJedis);
        }
        return true;
    }

    @Override
    public String getFromCache(String key) {
        ShardedJedis shardedJedis = redisDataSourceService.getRedisClient();
        try {
            return shardedJedis.get(key);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        } finally {
            redisDataSourceService.returnResource(shardedJedis);
        }
    }

    @SuppressWarnings("unchecked")
	@Override
    public <T> T getFromCache(String key, Class<T> clazz) {
    		byte[] ret;
    		ShardedJedis shardedJedis = redisDataSourceService.getRedisClient();
        try {
             ret = shardedJedis.get(key.getBytes());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        } finally {
            redisDataSourceService.returnResource(shardedJedis);
        }
        Object result = SerializeUtil.unserialize(ret);
        //Object result = JSON.parseObject(value, clazz);
        return (T) result;
    }
    
   
    @Override
    public boolean deleteCache(String key) {
        ShardedJedis shardedJedis = redisDataSourceService.getRedisClient();
        if (shardedJedis == null) {
            return false;
        }
        try {
            shardedJedis.del(key);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        } finally {
            redisDataSourceService.returnResource(shardedJedis);
        }
    }
    
    @Override
    public boolean deleteByPattern(String pattern){  
        
        TreeSet<String> keys = new TreeSet<>();  
         //获取所有的节点

        ShardedJedis shardedJedis = redisDataSourceService.getRedisClient();

        Collection<Jedis> set = shardedJedis.getAllShards();
        
        //遍历节点 获取所有符合条件的KEY 

        for(Jedis j : set) {  
            try {  
                keys.addAll(j.keys(pattern));  
            } catch(Exception e){  
                logger.error("Getting keys error: {}", e);  
            } finally{  
                logger.debug("Connection closed.");  
                j.close();//用完一定要close这个链接！！！  
            }  
        }  
        
        try {
        	
	        	for (String key : keys) {
	            	shardedJedis.del(key);
            }
        	
	        	return true;
        	
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        } finally {
            redisDataSourceService.returnResource(shardedJedis);
        }
        
   }  
    
    

    @Override
    public List<String> keysByPattern(String pattern){  
        
    		List<String> keys = new ArrayList<String>();  
         //获取所有的节点

        ShardedJedis shardedJedis = redisDataSourceService.getRedisClient();

        Collection<Jedis> set = shardedJedis.getAllShards();
        
        //遍历节点 获取所有符合条件的KEY 

        for(Jedis j : set) {  
            try {  
                keys.addAll(j.keys(pattern));  
            } catch(Exception e){  
                logger.error("Getting keys error: {}", e);  
            } finally{  
                logger.debug("Connection closed.");  
                j.close();//用完一定要close这个链接！！！  
            }  
        }  
        
        return keys;
    }

}
