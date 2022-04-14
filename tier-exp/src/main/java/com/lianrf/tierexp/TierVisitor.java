package com.lianrf.tierexp;

import com.lianrf.tierexp.common.ExceptionUtil;
import com.lianrf.tierexp.exception.TierParseException;
import com.lianrf.tierexp.exception.TierRunException;
import com.lianrf.tierexp.interpreter.ConstInterpreter;
import com.lianrf.tierexp.interpreter.Interpreter;
import com.lianrf.tierexp.parser.TierExpBaseVisitor;
import com.lianrf.tierexp.parser.TierExpParser;
import com.lianrf.tierexp.parser.TierExpVisitor;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * TierVisitor
 *
 * @author lianrf
 * @version 1.0
 * @since 2022/3/2 4:40 下午
 */
public class TierVisitor implements InvocationHandler {

    private static final Logger logger = LoggerFactory.getLogger(TierVisitor.class);

    private final TierExpVisitor<Object> tierExpVisitor;

    private TierVisitor(TierExpVisitor<Object> tierExpVisitor) {
        this.tierExpVisitor = tierExpVisitor;
    }

    public static TierExpVisitor<?> create() {
        ExpVisitor source = new ExpVisitor();
        TierVisitor tierVisitor = new TierVisitor(source);
        Object o = Proxy.newProxyInstance(TierVisitor.class.getClassLoader()
                , new Class[]{TierExpVisitor.class}, tierVisitor);
        TierExpVisitor<?> tierExpVisitor = (TierExpVisitor<?>) o;
        source.setTierExpVisitorProxy(tierExpVisitor);
        return tierExpVisitor;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Class<?> aClass = method.getDeclaringClass();
        if (Object.class.equals(aClass)) {
            return method.invoke(this, args);
        }
        if (TierExpVisitor.class.equals(aClass)) {
            if (args.length == 0) {
                //TODO 参数错误
            }
            ParseTree tree = get(args);

            MethodHandles.Lookup lookup = MethodHandles.publicLookup();

            Interpreter interpreter = null;
            try {
                VarHandle varHandle = lookup.findVarHandle(tree.getClass(), "interpreter", Interpreter.class);
                Object obj = varHandle.get(tree);
                interpreter = (Interpreter) obj;
                RunEnvironment environment = RunEnvironment.get();
                return interpreter.interpret(environment.getContext(), tree, tierExpVisitor);

            } catch (NoSuchFieldException e) {
                //调用method.invoke(tierExpVisitor, args);
            } catch (IllegalAccessException e) {
                throw new TierRunException("非法访问", e);
            }/* catch (TierParseException | TierRunException e) {
                throw new TierRunException(e.getMessage());
            }*/ catch (Exception e) {
                logger.error("解释器运行错误,当前解释器:{}",interpreter);
                throw e;
            }
        }

        try {
            return method.invoke(tierExpVisitor, args);
        } catch (Exception e) {
            throw ExceptionUtil.unwrapThrowable(e);
        }
    }

    private ParseTree get(Object[] args) {
        for (Object arg : args) {
            if (arg instanceof ParseTree) {
                return (ParseTree) arg;
            }
        }
        throw new TierParseException("未获取到解析树");
    }

    public static void main(String[] args) {

        TierExpParser.ExprContext exprContext = new TierExpParser.ExprContext();
        TierExpParser.ExprCallContext callContext = new TierExpParser.ExprCallContext(exprContext);
        callContext.interpreter = new ConstInterpreter();

        Object o = callContext;

        MethodHandles.Lookup lookup = MethodHandles.lookup();


    }

    public static class ExpVisitor extends TierExpBaseVisitor<Object> {

        private TierExpVisitor<?> tierExpVisitorProxy;

        private void setTierExpVisitorProxy(TierExpVisitor<?> tierExpVisitorProxy) {
            this.tierExpVisitorProxy = tierExpVisitorProxy;
        }

        @Override
        public Object visit(ParseTree tree) {
            return tree.accept(tierExpVisitorProxy);
        }

        @Override
        public Object visitChildren(RuleNode node) {
            Object result = defaultResult();
            int n = node.getChildCount();
            for (int i = 0; i < n; i++) {
                if (!shouldVisitNextChild(node, result)) {
                    break;
                }

                ParseTree c = node.getChild(i);
                Object childResult = c.accept(tierExpVisitorProxy);
                result = aggregateResult(result, childResult);
            }
            return result;
        }
    }
}
