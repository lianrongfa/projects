package com.lianrf.tierexp.instruction.op;

import com.lianrf.tierexp.context.ExpContext;
import com.lianrf.tierexp.parser.TierExpVisitor;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 * TODO
 *
 * @author lianrf
 * @version 1.0
 * @since 2022/3/11 3:08 下午
 */
public abstract class OperatorAbs implements Operator {

    protected String name;

    protected String aliasName;

    /**
     * 是否需要高精度计算
     */
    protected boolean isPrecise;

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Object call(ExpContext context, ParseTree node, TierExpVisitor<Object> visitor) {


        return null;
    }

    @Override
    public Class<? extends ParseTree> getTreeClass() {
        return null;
    }

    public abstract Object call(Object[] args);

}
