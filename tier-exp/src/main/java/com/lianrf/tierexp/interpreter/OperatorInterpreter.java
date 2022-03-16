package com.lianrf.tierexp.interpreter;

import com.lianrf.tierexp.context.ExpContext;
import com.lianrf.tierexp.instruction.op.Operator;
import com.lianrf.tierexp.parser.TierExpVisitor;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 * TODO
 *
 * @author lianrf
 * @version 1.0
 * @since 2022/3/11 2:47 下午
 */
public class OperatorInterpreter implements Interpreter{
    private Operator operator;

    @Override
    public Object interpret(ExpContext context, ParseTree node, TierExpVisitor<Object> visitor) {
        return operator.call(context,node,visitor);
    }
}
