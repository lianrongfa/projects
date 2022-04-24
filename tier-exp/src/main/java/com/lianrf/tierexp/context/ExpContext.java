package com.lianrf.tierexp.context;

import com.lianrf.tierexp.TierExpEngineImpl;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * 非线程安全，不同线程必须各自持有各自的ExpContext
 *
 * @author lianrf
 * @version 1.0
 * @since 2022/2/17 4:18 下午
 */
public interface ExpContext<K,V> {

    /**
     * 获取变量值
     *
     * @param key 变量名称
     * @return 变量值，如果没有找到变量返回null
     */
    V get(Object key);

    /**
     * 设置变量
     *
     * @param key  变量名称
     * @param value 变量值
     */
    V put(K key, V value);

    /**
     * 获取迭代器
     * @return 迭代器
     */
    ParseTreeVisitor<Object> getVisitor();

    /**
     * 获取engine
     * @return TierExpEngineImpl
     */
    TierExpEngineImpl getEngine();

    /**
     * 设置engine
     * @param engine TierExpEngineImpl
     */
    void setEngine(TierExpEngineImpl engine);
}
