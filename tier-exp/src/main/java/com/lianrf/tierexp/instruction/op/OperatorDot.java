package com.lianrf.tierexp.instruction.op;

import com.lianrf.tierexp.context.ExpContext;
import com.lianrf.tierexp.parser.TierExpVisitor;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 * attr
 *
 * @author lianrf
 * @version 1.0
 * @since 2022/3/11 5:42 下午
 */
public class OperatorDot extends OperatorAbs {

    public OperatorDot(String name) {
        this.name = name;
    }


    @Override
    public Object call(ExpContext context, ParseTree node, TierExpVisitor<Object> visitor) {




        return null;
    }

    @Override
    public Object call(Object[] list) {
        return null;
    }
}
