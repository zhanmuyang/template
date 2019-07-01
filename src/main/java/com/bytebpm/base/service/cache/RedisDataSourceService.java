package com.bytebpm.base.service.cache;

import redis.clients.jedis.ShardedJedis;

public interface RedisDataSourceService {

    public abstract ShardedJedis getRedisClient();

    public void returnResource(ShardedJedis shardedJedis);

}
