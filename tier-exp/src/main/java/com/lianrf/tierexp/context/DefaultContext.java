package com.lianrf.tierexp.context;

import com.lianrf.tierexp.TierExpEngineImpl;
import com.lianrf.tierexp.TierVisitor;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lianrf
 * @version 1.0
 * @since 2022/2/17 5:54 下午
 */
public class DefaultContext extends HashMap<String, Object> implements ExpContext<String, Object> {

    protected transient ParseTreeVisitor<Object> visitor;

    protected transient TierExpEngineImpl engine;

    public DefaultContext() {

    }

    public DefaultContext(Map<String, Object> map) {
        if (map != null) {
            this.putAll(map);
        }
    }

    @Override
    public ParseTreeVisitor<Object> getVisitor() {
        if (this.visitor == null) {
            TierVisitor vis = new TierVisitor();
            vis.setContext(this);
            this.visitor = vis;
        }
        return this.visitor;
    }

    @Override
    public TierExpEngineImpl getEngine() {
        return this.engine;
    }

    public void setEngine(TierExpEngineImpl engine) {
        this.engine = engine;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
