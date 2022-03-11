package com.lianrf.tierexp;

import com.lianrf.tierexp.context.ExpContext;
import com.lianrf.tierexp.context.MapContext;
import com.lianrf.tierexp.parser.ArParser;
import com.lianrf.tierexp.parser.IParser;
import com.lianrf.tierexp.parser.TierExpVisitor;

/**
 * TODO
 *
 * @author lianrf
 * @version 1.0
 * @since 2022/2/24 3:21 下午
 */
public class TierExpEngineImpl implements TierExpEngine {

    private ExpContext context;

    private IParser parser;

    private final TierExpVisitor<?> visitor;

    public TierExpEngineImpl() {
        this(new MapContext());
    }

    public TierExpEngineImpl(ExpContext context) {
        this.context = context;
        this.parser = new ArParser(null, this);
        this.visitor= TierVisitor.create();
    }


    @Override
    public Object run(String exp) {
        return this.run(exp,this.context);
    }

    @Override
    public Object run(String exp, ExpContext ctx) {
        InstructionNode instructionNode = this.parser.parse(exp);
        return instructionNode.accept(visitor,ctx);
    }

    @Override
    public InstructionNode parse(String exp) {
        return this.parser.parse(exp);
    }


    public ExpContext getContext() {
        return context;
    }

    public void setContext(ExpContext context) {
        this.context = context;
    }

    public IParser getParser() {
        return parser;
    }

    public void setParser(IParser parser) {
        this.parser = parser;
    }
}
