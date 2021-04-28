package cn.lianrf.service.loader;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @version: v1.0
 * @date: 2019/10/12
 * @author: lianrf
 */
public interface LoaderInterface<T> {

    default void test(T t){
    }


}
