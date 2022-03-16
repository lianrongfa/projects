package com.lianrf.tierexp.parser;

/**
 * TierExp配置
 *
 * @author lianrf
 * @version 1.0
 * @since 2022/2/24 3:13 下午
 */
public class Configuration {

    /**
     * 是否输出所有的跟踪信息，同时还需要log级别是DEBUG级别
     */
    private boolean isTrace;

    /**
     * 是否使用逻辑短路特性增强质量的效率
     */
    private boolean isShortCircuit;

    /**
     * 是否需要高精度计算
     */
    private boolean isPrecise;








    public boolean isTrace() {
        return isTrace;
    }

    public void setTrace(boolean trace) {
        isTrace = trace;
    }

    public boolean isShortCircuit() {
        return isShortCircuit;
    }

    public void setShortCircuit(boolean shortCircuit) {
        isShortCircuit = shortCircuit;
    }

    public boolean isPrecise() {
        return isPrecise;
    }

    public void setPrecise(boolean precise) {
        isPrecise = precise;
    }
}
