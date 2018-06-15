package cn.lianrf.old;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by lianrongfa on 2018/6/15.
 * 持久监听
 */
public class PerpetualWatcher {


    public static void main(String[] args) {
        try {
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            ZooKeeper zooKeeper = new ZooKeeper("120.77.243.179:2181", 4000, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    System.out.println("connection success");
                    countDownLatch.countDown();
                }
            }
            );
            countDownLatch.await();

            Perpetual perpetual = new Perpetual(zooKeeper);

            zooKeeper.exists(getPath(),perpetual);

            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

    private static String getPath() {
        return "/lianrf";
    }

    static class Perpetual implements Watcher {

        private ZooKeeper zooKeeper;

        public Perpetual(ZooKeeper zooKeeper) {
            this.zooKeeper = zooKeeper;
        }

        @Override
        public void process(WatchedEvent event) {
            try {
                System.out.println(event.getPath()+":"+event.getType());
                zooKeeper.exists("/lianrf", this);
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
class MicDemo{
    public static void main1(String[] args) throws IOException, InterruptedException, KeeperException {
        final CountDownLatch countDownLatch=new CountDownLatch(1);
        final ZooKeeper zooKeeper=
                new ZooKeeper("120.77.243.179:2181",4000, new Watcher() {
                    @Override
                    public void process(WatchedEvent event) {
                        System.out.println("默认事件： "+event.getType());
                        if(Event.KeeperState.SyncConnected==event.getState()){
                            //如果收到了服务端的响应事件，连接成功
                            countDownLatch.countDown();
                        }
                    }
                });
        countDownLatch.await();

        /*zooKeeper.create("/zk-persis-mic","1".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);*/


        //exists  getdata getchildren
        //通过exists绑定事件
        Stat stat=zooKeeper.exists("/lianrf", new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println(event.getType()+"->"+event.getPath());
                try {
                    //再一次去绑定事件
                    zooKeeper.exists(event.getPath(),this);
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


        System.in.read();
    }

}