package cn.lianrf.redis;

import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * Created by lianrongfa on 2018/5/3.
 */
public class RedisTest {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("120.77.243.179", 6379);
        List<String> lrange = jedis.lrange("123", 0, 0);
        System.out.println(lrange);

    }
}
