package com.jiang.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtils {

    private static final JedisPool jedisPool;

    private static Jedis jedis;

    static {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(200);
        jedisPoolConfig.setMaxIdle(50);
        jedisPoolConfig.setMinIdle(30);
        jedisPoolConfig.setMaxWaitMillis(1000 * 200000);
        jedisPoolConfig.setTestOnBorrow(true);
        jedisPoolConfig.setTestOnCreate(true);
        jedisPoolConfig.setTestOnReturn(true);
        jedisPool = new JedisPool(jedisPoolConfig, "127.0.0.1", 6379, 100000);
    }


    /**
     * 获取 Jedis
     *
     * @return
     */
    public static Jedis jedis() {
        jedis = jedisPool.getResource();
        return jedis;
    }

    public static void close() {
        if (null != jedis) {
            jedis.close();
        }
    }

}
