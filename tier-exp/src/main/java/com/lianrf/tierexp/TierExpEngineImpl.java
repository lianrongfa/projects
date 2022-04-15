package com.lianrf.tierexp;

import com.lianrf.tierexp.context.ContextChain;
import com.lianrf.tierexp.context.ExpContext;
import com.lianrf.tierexp.context.MapContext;
import com.lianrf.tierexp.exception.TierParseException;
import com.lianrf.tierexp.exception.TierRunException;
import com.lianrf.tierexp.instruction.OperatorManager;
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

    /**
     * 操作符的管理器
     */
    private OperatorManager opManager;

    public TierExpEngineImpl(boolean isPrecise) {
        this(isPrecise,new ContextChain(null, new MapContext()));
    }

    public TierExpEngineImpl(boolean isPrecise,ExpContext context) {
        this.context = context;
        this.parser = new ArParser(null, this);
        this.visitor = TierVisitor.create();
        this.opManager = new OperatorManager(isPrecise);
    }


    @Override
    public Object run(String exp) throws TierRunException, TierParseException {
        return this.run(exp, this.context);
    }

    @Override
    public Object run(String exp, ExpContext ctx) throws TierRunException, TierParseException {
        InstructionNode instructionNode = this.parser.parse(exp);
        return instructionNode.accept(visitor, ctx, this);
    }

    public Object run(InstructionNode instructionNode, ExpContext ctx) throws TierRunException, TierParseException {
        return instructionNode.accept(visitor, ctx, this);
    }

    @Override
    public InstructionNode parse(String exp) throws TierParseException{
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

    public OperatorManager getOpManager() {
        return opManager;
    }

    public void setOpManager(OperatorManager opManager) {
        this.opManager = opManager;
    }
}
