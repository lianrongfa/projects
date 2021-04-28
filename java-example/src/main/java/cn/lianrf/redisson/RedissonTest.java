package cn.lianrf.redisson;

import org.redisson.Redisson;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.TransportMode;

/**
 * Created by lianrongfa on 2018/5/4.
 */
public class RedissonTest {
    public static void main(String[] args) {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://120.77.243.179:6379");
        RedissonClient client = Redisson.create(config);

        System.out.println(client);
    }
}
