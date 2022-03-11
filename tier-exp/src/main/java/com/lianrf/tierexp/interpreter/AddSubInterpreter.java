package com.lianrf.tierexp.interpreter;

import com.lianrf.tierexp.context.ExpContext;
import com.lianrf.tierexp.parser.TierExpVisitor;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 * TODO
 *
 * @author lianrf
 * @version 1.0
 * @since 2022/3/11 10:12 上午
 */
public class AddSubInterpreter implements Interpreter {



    public AddSubInterpreter(ParseTree node) {

    }

    @Override
    public Object interpret(ExpContext context, ParseTree node, TierExpVisitor<Object> visitor) {

        ParseTree left = node.getChild(0);

        Object leftV = visitor.visit(left);

        ParseTree right = node.getChild(2);

        Object rightV = visitor.visit(right);

        return Double.parseDouble(leftV.toString())+Double.parseDouble(rightV.toString());
    }
}
