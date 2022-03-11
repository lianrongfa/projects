package com.lianrf.tierexp.instruction.op;

import com.lianrf.tierexp.context.ExpContext;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 * TODO
 *
 * @author lianrf
 * @version 1.0
 * @since 2022/3/11 3:08 下午
 */
public abstract class OperatorAbs implements Operator{

    protected String name;


    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Object call(ParseTree tree, ExpContext context) {
        return null;
    }

    @Override
    public Class<? extends ParseTree> getTreeClass() {
        return null;
    }



}
