package com.bytebpm.base.service.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import javax.inject.Inject;

@Service("redisDataSourceService")
public class RedisDataSourceServiceImpl implements RedisDataSourceService {

    private static final Logger logger = LoggerFactory.getLogger(RedisDataSourceServiceImpl.class);

    @Autowired
    private ShardedJedisPool shardedJedisPool;

    @Override
    public ShardedJedis getRedisClient() {
        try {
            ShardedJedis shardJedis = shardedJedisPool.getResource();
            JedisPool jedisPool = new JedisPool();
            Jedis jedis = jedisPool.getResource();
            return shardJedis;
        } catch (Exception e) {
            logger.error("getRedisClent error", e);
        }
        return null;
    }

    @Override
    public void returnResource(ShardedJedis shardedJedis) {
        if (shardedJedis != null) {
            shardedJedis.close();
        }
    }

}
