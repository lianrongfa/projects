package com.lianrf.tierexp.interpreter.struct;

import com.lianrf.tierexp.exception.TierParseException;
import com.lianrf.tierexp.interpreter.ConstInterpreter;
import com.lianrf.tierexp.interpreter.IndexInterpreter;
import com.lianrf.tierexp.interpreter.Interpreter;
import com.lianrf.tierexp.interpreter.OperatorInterpreter;
import com.lianrf.tierexp.interpreter.ParensInterpreter;
import com.lianrf.tierexp.interpreter.VarInterpreter;
import com.lianrf.tierexp.parser.TierExpParser;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.util.function.BiFunction;

/**
 * InterpreterFactor
 *
 * @author lianrf
 * @version 1.0
 * @since 2022/3/7 4:58 下午
 */
public class SimpleInterpreterFactory implements InterpreterFactory {

    private static final Class<?>[] OP_CLASS = {Token.class, TierExpParser.ExprContext.class};

    @Override
    public Interpreter create(ParseTree tree) {
        Class<? extends ParseTree> treeClass = tree.getClass();
        String name = treeClass.getName();

        if ("com.lianrf.tierexp.parser.TierExpParser$ExprIdContext".equals(name)) {
            return new VarInterpreter(tree);
        }
        if ("com.lianrf.tierexp.parser.TierExpParser$ExprParensContext".equals(name)) {
            return new ParensInterpreter(tree);
        }
        if ("com.lianrf.tierexp.parser.TierExpParser$ExprLiteralsContext".equals(name)) {
            return new ConstInterpreter(tree);
        }
        if ("com.lianrf.tierexp.parser.TierExpParser$ExprIndexContext".equals(name)) {
            return new IndexInterpreter(tree);
        }

        VarHandle varHandle = getVarHandle(tree, treeClass);
        Object opO = varHandle.get(tree);

        return new OperatorInterpreter(opO,tree);

    }

    private VarHandle getVarHandle(ParseTree tree, Class<? extends ParseTree> treeClass) {
        MethodHandles.Lookup lookup = MethodHandles.publicLookup();
        VarHandle varHandle = null;
        for (Class<?> aClass : OP_CLASS) {
            try {
                varHandle = lookup.findVarHandle(treeClass, "op", aClass);
                break;
            } catch (NoSuchFieldException e) {
                //todo
            } catch (IllegalAccessException e) {
                throw new TierParseException(e.getMessage(), e);
            }
        }
        if(varHandle==null){
            throw new TierParseException("未知的解释器:"+ tree);
        }
        return varHandle;
    }

    class TreeMapIter {
        private String clazzPrefix;

        private String clazzName;

        private String aliasName;

        private BiFunction biFunction;
    }


}
