package com.lianrf.tierexp.context;

/**
 * 变量
 *
 * @author lianrf
 * @version 1.0
 * @since 2022/2/17 4:18 下午
 */
public class Var {

    public Var(String name, Object value) {
        this(name, value, null);
    }

    public Var(String name, Object value, Class<?> type) {
        this.name = name;
        this.value = value;
        this.type = type;
    }

    private Class<?> getTypeByValue() {
        return value != null ? value.getClass() : null;
    }

    /**
     * 变量名称
     */
    private String name;

    /**
     * 变量值
     */
    private Object value;

    /**
     * 变量类型
     */
    private Class<?> type;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Class<?> getType() {
        return type != null ? type : getTypeByValue();
    }

    public void setType(Class<?> type) {
        this.type = type;
    }
}
