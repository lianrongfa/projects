package cn.lianrf.old;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.List;

/**
 * Created by lianrongfa on 2018/6/15.
 */
public class CRUDDemo {
    public static void main(String[] args) {
        ZooKeeper zooKeeper = ConnectionDemo.getConnection();
        try {
            //C
            zooKeeper.create(getPath()+"/code", "0".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            Thread.sleep(2000);

            //R
            Stat stat = new Stat();
            List<String> children = zooKeeper.getChildren(getPath(), true);
            children.forEach(s -> {
                System.out.println("path:"+s);
                byte[] data = new byte[0];
                try {
                    data = zooKeeper.getData(getPath(), true,null);
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("data:"+new String(data));
            });

            //U
            stat = zooKeeper.setData(getPath()+"/"+children.get(0), "1".getBytes(), stat.getVersion());

            //D
            //zooKeeper.delete(getPath(),stat.getVersion());

            zooKeeper.close();
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static String getPath() {
        return "/lianrf";
    }
}
