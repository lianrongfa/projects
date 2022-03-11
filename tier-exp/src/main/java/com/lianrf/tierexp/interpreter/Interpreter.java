package com.lianrf.tierexp.interpreter;


import com.lianrf.tierexp.context.ExpContext;
import com.lianrf.tierexp.parser.TierExpVisitor;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 * 用于解析Node的值
 */
public interface Interpreter {

    Object interpret(ExpContext context, ParseTree node, TierExpVisitor<Object> visitor);

}
