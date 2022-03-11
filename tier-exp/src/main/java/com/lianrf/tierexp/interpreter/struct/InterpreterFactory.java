package com.lianrf.tierexp.interpreter.struct;

import com.lianrf.tierexp.interpreter.Interpreter;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 * InterpreterFactor
 *
 * @author lianrf
 * @version 1.0
 * @since 2022/3/7 4:58 下午
 */
public interface InterpreterFactory {

    Interpreter create(ParseTree tree);


}
