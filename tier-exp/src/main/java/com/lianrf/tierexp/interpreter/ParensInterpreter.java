package com.lianrf.tierexp.interpreter;

import com.lianrf.tierexp.context.ExpContext;
import com.lianrf.tierexp.parser.TierExpVisitor;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 * @author lianrf
 * @version 1.0
 * @since 2022/3/7 3:12 下午
 */
public class ParensInterpreter implements Interpreter {

    private final ParseTree node;

    public ParensInterpreter(ParseTree node) {
        this.node = node.getChild(1);
    }

    @Override
    public Object interpret(ExpContext context, ParseTree node, TierExpVisitor<Object> visitor) {
        return visitor.visit(this.node);
    }
}
