package cn.lianrf.old;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by lianrongfa on 2018/6/12.
 * <p>
 * 客户端注册监听有三种方式 ： getData exists getChildren
 */
public class WatcherDemo {
    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(1);
        ZooKeeper zooKeeper=null;
        try {
            zooKeeper = new ZooKeeper("120.77.243.179:2181", 4000, (x)->{
                System.out.println("watcher ... "+x.getType());
            });
            String s = zooKeeper.create("/lianrf", "0".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            zooKeeper.getData("/lianrf",true,null);
            zooKeeper.exists("/lianrf",true);
            zooKeeper.setData("/lianrf","0".getBytes(),-1);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }

        Thread.sleep(10000);
        //zooKeeper.create("/mic","0".getBytes(), ZooDefs.Ids. OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);//创建节点
    }

}
