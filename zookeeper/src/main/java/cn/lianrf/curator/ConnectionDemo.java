package cn.lianrf.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * Created by lianrongfa on 2018/6/15.
 * <p>
 * curator-framework：对zookeeper的底层api的一些封装
 * curator-client：提供一些客户端的操作，例如重试策略等
 * curator-recipes：封装了一些高级特性，如：Cache事件监听、选举、分布式锁、分布式计数器、分布式Barrier等
 */
public class ConnectionDemo {
    public static void main(String[] args) {
        CuratorFramework curator = CuratorFrameworkFactory.builder()
                .connectString("120.77.243.179:2181")
                .sessionTimeoutMs(4000).namespace("lianrf")
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .build();

        curator.start();
        try {
            //C
            String mm = curator.create().forPath("/mm", "1".getBytes());
            System.out.println("create:"+mm);

            //R
            System.out.println("namespace:"+curator.getNamespace());

            byte[] bytes = curator.getData().forPath("/mm");
            String s = new String(bytes,"UTF-8");
            System.out.println("data:"+s);

            //U
            curator.setData().forPath("/mm", "2".getBytes());

            //D
            curator.delete().forPath("/mm");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
