package cn.lianrf.queue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Created by lianrongfa on 2018/1/22.
 */
public class Resource<T> implements Delayed {
    private T source;
    private long liveTime;
    private long removeTime;

    @Override
    public int compareTo(Delayed o) {
        return 0;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return 0;
    }

    public T getSource() {
        return source;
    }

    public void setSource(T source) {
        this.source = source;
    }
}
