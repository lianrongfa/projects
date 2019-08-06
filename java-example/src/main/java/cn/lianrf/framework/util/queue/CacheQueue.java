package cn.lianrf.framework.util.queue;

import java.io.Serializable;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Created by lianrongfa on 2018/1/26.
 *
 * 超时自动销毁队列
 */
public class CacheQueue<V> implements Cache<V>,Serializable{
    //维护队列所用
    private Queue<Resource<V>> queue;
    //队列有效元素
    private Queue<Resource<V>> sourceQueue;

    public CacheQueue() {
        queue = new DelayQueue<Resource<V>>();
        sourceQueue=new ConcurrentLinkedQueue<Resource<V>>();
        Thread t = new Thread(()->{
            keepCache();
        });
        //设置为守护线程
        t.setDaemon(true);
        t.start();
    }


    @Override
    public boolean add(V v, int time) {
        try {
            Resource<V> item = new Resource<>(v, time);
            queue.add(item);
            sourceQueue.add(item);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public V get(){
        Resource<V> poll = sourceQueue.poll();
        V source =null;
        if(poll!=null){
            source = poll.getSource();
        }
        return source;
    }


    public void keepCache(){
        while (true){
            Resource<V> poll = queue.poll();
            if(poll!=null){
                sourceQueue.remove(poll);
                System.out.println("remove item:"+poll.getSource());
            }else{
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    class Resource<T> implements Delayed,Serializable {
        private T source;
        private int liveTime;//单位分钟
        private long removeTime;//单位毫秒

        /**
         *
         * @param source
         * @param liveTime
         */
        public Resource(T source, int liveTime) {
            this.source = source;
            this.liveTime = liveTime;
            this.removeTime= TimeUnit.MILLISECONDS.convert(liveTime,TimeUnit.MINUTES)+System.currentTimeMillis();
        }

        @Override
        public int compareTo(Delayed o) {
            if (o == null) return 1;
            if (o == this) return 0;
            if(o instanceof Resource){
                Resource<T> o1 = (Resource<T>) o;
                if (liveTime > o1.liveTime) {
                    return 1;
                } else if (liveTime == o1.liveTime) {
                    return 0;
                } else {
                    return -1;
                }
            }

            long l = this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS);
            return l > 0 ? 1 : l == 0 ? 0 : -1;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(this.removeTime - System.currentTimeMillis(),unit);
        }

        public T getSource() {
            return source;
        }

        public void setSource(T source) {
            this.source = source;
        }
    }

}
