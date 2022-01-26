package com.jiang.service.imp;

import com.jiang.service.BusinessService;
import com.jiang.util.RedisUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisDataException;

public class BusinessServiceImpl implements BusinessService {
    @Override
    public void speedLimit(String id, String name, Long limitSeconds, Long count) {
        Jedis jedis = RedisUtils.jedis();
        String key = "compId:"+id;
        try {
            /* 判断该值是否存在 */
            if (!jedis.exists(key)) {
                // 不存在
                String setex = jedis.setex(key, Math.toIntExact(limitSeconds), String.valueOf(Long.MAX_VALUE - count));
            } else {
                // 存在，自增并调用业务
                Long incr = jedis.incr(key);
                this.business(id, name, count - (Long.MAX_VALUE - incr));
            }
        }catch (JedisDataException e){
            System.out.println("用户" + name + "使用已经到达次数上限，请升级会员级别");
            return;
        }finally {
            RedisUtils.close();
        }


    }

    @Override
    public void business(String id, String name, Long incr) {
        System.out.println("用户id:"+id+"用户名："+name+"业务操作执行了。。。，第"+incr+"次");
    }
}
