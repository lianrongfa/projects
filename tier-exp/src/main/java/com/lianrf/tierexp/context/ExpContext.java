package com.lianrf.tierexp.context;

/**
 * 非线程安全，不同线程必须各自持有各自的ExpContext
 *
 * @author lianrf
 * @version 1.0
 * @since 2022/2/17 4:18 下午
 */
public interface ExpContext {

    /**
     * 获取变量值
     *
     * @param name 变量名称
     * @return 变量值，如果没有找到变量返回null
     */
    Object get(String name);

    /**
     * 设置变量
     *
     * @param name  变量名称
     * @param value 变量值
     */
    void put(String name, Object value);

    Var getVar(String name);

    void setVar(Var v);
}
