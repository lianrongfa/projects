package com.lianrf.tierexp;

import com.lianrf.tierexp.context.ExpContext;

/**
 * 运行时环境
 *
 * @author lianrf
 * @version 1.0
 * @since 2022/3/14 3:52 下午
 */
public final class RunEnvironment {

    private static final ThreadLocal<RunEnvironment> ENV = ThreadLocal
            .withInitial(RunEnvironment::new);


    public static void putAll(TierExpEngineImpl engine, ExpContext context) {
        RunEnvironment env = ENV.get();
        env.setEngine(engine);
        env.setContext(context);
    }

    public static void putEng(TierExpEngineImpl engine) {
        RunEnvironment env = ENV.get();
        env.setEngine(engine);
    }

    public static void putContext(ExpContext context) {
        RunEnvironment env = ENV.get();
        env.setContext(context);
    }

    public static RunEnvironment get() {
        return ENV.get();
    }

    public static void clear() {
        ENV.remove();
    }

    private TierExpEngineImpl engine;

    private ExpContext context;


    public TierExpEngineImpl getEngine() {
        return engine;
    }

    public void setEngine(TierExpEngineImpl engine) {
        this.engine = engine;
    }

    public ExpContext getContext() {
        return context;
    }

    public void setContext(ExpContext context) {
        this.context = context;
    }
}
