package cn.lianrf.old;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by lianrongfa on 2018/6/15.
 */
public class ConnectionDemo {
    public static void main(String[] args) {
        getConnection();
    }

    public static ZooKeeper getConnection() {
        try {
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            ZooKeeper zooKeeper = new ZooKeeper("120.77.243.179:2181",50000,new Watcher() {
                @Override
                public void
                process(WatchedEvent event) {
                    if (Event.KeeperState.SyncConnected == event.getState()) {
                        //如果收到了服务端的响应事件，连接成功
                        countDownLatch.countDown();
                    }
                    System.out.println(event.getType());
                }
            });
            countDownLatch.await();
            System.out.println(zooKeeper.getState());//CONNECTING
            return zooKeeper;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
