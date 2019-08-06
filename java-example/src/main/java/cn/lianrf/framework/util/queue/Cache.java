package cn.lianrf.framework.util.queue;

/**
 * Created by lianrongfa on 2018/2/1.
 */
public interface  Cache<V> {
    /**
     * 增加元素
     * @param v 增加的元素
     * @param time 延时时间
     * @return
     */
    boolean add(V v,int time);

    /**
     * 得到元素
     * @return
     */
     V get();

    /**
     * 维持队列元素有效性
     */
    void keepCache();
}
