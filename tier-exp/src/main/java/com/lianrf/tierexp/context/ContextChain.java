//package com.lianrf.tierexp.context;
//
///**
// * ContextChain
// *
// * @author lianrf
// * @version 1.0
// * @since 2022/4/8 4:57 下午
// */
//public class ContextChain implements ExpContext<String, Object> {
//
//    /**
//     * 上级context
//     */
//    private final ExpContext<String, Object> parent;
//    /**
//     * 本级context
//     */
//    private final ExpContext<String, Object> current;
//
//
//    public ContextChain(ExpContext<String, Object> parent, ExpContext<String, Object> current) {
//        this.parent = parent;
//        this.current = current;
//    }
//
//    @Override
//    public Object put(String name, Object value) {
//        return current.put(name, value);
//    }
//
//    @Override
//    public Object get(Object key) {
//        Object object = current.get(key);
//        if (object != null) {
//            return object;
//        }
//        if (parent != null) {
//            return parent.get(key);
//        }
//        return null;
//    }
//}
