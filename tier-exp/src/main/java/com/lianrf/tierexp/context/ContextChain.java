package com.lianrf.tierexp.context;

/**
 * ContextChain
 *
 * @author lianrf
 * @version 1.0
 * @since 2022/4/8 4:57 下午
 */
public class ContextChain implements ExpContext {

    /**
     * 上级context
     */
    private final ExpContext parent;
    /**
     * 本级context
     */
    private final ExpContext current;


    public ContextChain(ExpContext parent, ExpContext current) {
        this.parent = parent;
        this.current = current;
    }

    @Override
    public Object get(String name) {
        Var v = this.getVar(name);
        return v != null ? v.getValue() : null;
    }

    @Override
    public void put(String name, Object value) {
        current.put(name, value);
    }

    @Override
    public Var getVar(String name) {
        Var object = current.getVar(name);
        if (object != null) {
            return object;
        }
        if (parent != null) {
            return parent.getVar(name);
        }
        return null;
    }

    @Override
    public void setVar(Var v) {
        current.setVar(v);
    }
}
