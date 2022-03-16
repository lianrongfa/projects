package com.lianrf.tierexp.interpreter.struct;

import com.lianrf.tierexp.interpreter.CentreInterpreter;
import com.lianrf.tierexp.interpreter.ConstInterpreter;
import com.lianrf.tierexp.interpreter.IndexInterpreter;
import com.lianrf.tierexp.interpreter.Interpreter;
import com.lianrf.tierexp.interpreter.ParensInterpreter;
import com.lianrf.tierexp.interpreter.VarInterpreter;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.function.BiFunction;

/**
 * InterpreterFactor
 *
 * @author lianrf
 * @version 1.0
 * @since 2022/3/7 4:58 下午
 */
public class SimpleInterpreterFactory implements InterpreterFactory {

    @Override
    public Interpreter create(ParseTree tree) {
        String name = tree.getClass().getName();

        if ("com.lianrf.tierexp.parser.TierExpParser$ExprIdContext".equals(name)) {
            return new VarInterpreter(tree);
        }
        if ("com.lianrf.tierexp.parser.TierExpParser$ExprParensContext".equals(name)) {
            return new ParensInterpreter(tree);
        }
        if ("com.lianrf.tierexp.parser.TierExpParser$ExprLiteralsContext".equals(name)) {
            return new ConstInterpreter(tree);
        }
        if ("com.lianrf.tierexp.parser.TierExpParser$ExprAddSubContext".equals(name)) {
            return new CentreInterpreter(tree);
        }
        if ("com.lianrf.tierexp.parser.TierExpParser$ExprIndexContext".equals(name)) {
            return new IndexInterpreter(tree);
        }


        return new ConstInterpreter();

    }

    class TreeMapIter {
        private String clazzPrefix;

        private String clazzName;

        private String aliasName;

        private BiFunction biFunction;
    }


}
