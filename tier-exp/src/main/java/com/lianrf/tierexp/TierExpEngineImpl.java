package com.lianrf.tierexp;

import com.lianrf.tierexp.common.SimpleCache;
import com.lianrf.tierexp.context.ExpContext;
import com.lianrf.tierexp.exception.TierParseException;
import com.lianrf.tierexp.exception.TierRunException;
import com.lianrf.tierexp.instruction.OperatorManager;
import com.lianrf.tierexp.parser.ArParser;
import com.lianrf.tierexp.parser.Configuration;
import com.lianrf.tierexp.parser.IParser;

/**
 * TierExpEngineImpl
 *
 * @author lianrf
 * @version 1.0
 * @since 2022/2/24 3:21 下午
 */
public class TierExpEngineImpl implements TierExpEngine {

    private IParser parser;

    private final Configuration configuration;
    /**
     * 操作符的管理器
     */
    private OperatorManager opManager;

    private SimpleCache<String, InstructionNode> INSTRUCTION_CACHE = new SimpleCache<>();

    public TierExpEngineImpl(boolean isPrecise) {
        this(Configuration.newBuilder().isPrecise(isPrecise).build());
    }

    public TierExpEngineImpl(Configuration configuration) {
        this.configuration = configuration;
        this.opManager = new OperatorManager(this.configuration);
        this.parser = new ArParser(configuration, this);
    }

    @Override
    public Object run(String exp, ExpContext<String, Object> ctx) throws TierRunException, TierParseException {
        InstructionNode instruction = this.parse(exp);
        return instruction.accept(ctx, this);
    }

    public Object run(InstructionNode instructionNode, ExpContext<String, Object> ctx) throws TierRunException, TierParseException {
        return instructionNode.accept(ctx, this);
    }

    @Override
    public InstructionNode parse(String exp) throws TierParseException {
        InstructionNode instruction = INSTRUCTION_CACHE.get(exp);
        if (instruction == null) {
            //此处多线程有小影响，问题不大
            instruction = this.parser.parse(exp);
            INSTRUCTION_CACHE.put(exp, instruction);
        }
        return instruction;
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

    public Configuration getConfiguration() {
        return configuration;
    }
}
