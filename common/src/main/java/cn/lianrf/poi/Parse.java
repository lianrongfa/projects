package cn.lianrf.poi;

/**
 * Created by lianrongfa on 2018/3/22.
 */
public interface Parse<T> {
    ParseStatus parseWork(T t);
}
