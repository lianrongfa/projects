package com.lianrf.tierexp.interpreter;

import com.lianrf.tierexp.context.ExpContext;
import com.lianrf.tierexp.exception.TierParseException;
import com.lianrf.tierexp.exception.TierRunException;
import com.lianrf.tierexp.parser.TierExpParser;
import com.lianrf.tierexp.parser.TierExpVisitor;
import org.antlr.v4.runtime.tree.ParseTree;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 数组
 *
 * @author lianrf
 * @version 1.0
 * @since 2022/3/11 10:12 上午
 */
public class IndexInterpreter implements Interpreter {

    private static final Set<Class<?>> FIRST_SET = new HashSet<>();

    private static final Set<Class<?>> INDEX_SET = new HashSet<>();

    static {
        FIRST_SET.add(TierExpParser.ExprIdContext.class);
        FIRST_SET.add(TierExpParser.ExprCallContext.class);
        FIRST_SET.add(TierExpParser.ExprParensContext.class);
        FIRST_SET.add(TierExpParser.ExprAttrContext.class);
        FIRST_SET.add(TierExpParser.ExprIndexContext.class);

        INDEX_SET.add(TierExpParser.ExprIdContext.class);
        INDEX_SET.add(TierExpParser.ExprLiteralsContext.class);
    }

    private ParseTree first;

    private ParseTree index;

    public IndexInterpreter(ParseTree node) {
        ParseTree symbol = node.getChild(0);
        if (FIRST_SET.contains(symbol.getClass())) {
            this.first = symbol;
        } else {
            //todo 异常提示
            throw new TierParseException();
        }

        ParseTree id = node.getChild(2);
        Class<? extends ParseTree> idClass = id.getClass();
        if (INDEX_SET.contains(idClass)) {
            literal(id, idClass);
        } else {
            //todo 异常提示
            throw new TierParseException();
        }
    }

    private void literal(ParseTree id, Class<? extends ParseTree> idClass) {
        if (TierExpParser.ExprLiteralsContext.class.equals(idClass)) {
            ParseTree idTree = id.getChild(0).getChild(0);
            if (!TierExpParser.IntContext.class.equals(idTree.getClass())) {
                //to do
                throw new TierParseException();
            }
        }
        this.index = id;
    }

    @Override
    public Object interpret(ExpContext<String, Object> context, ParseTree node, TierExpVisitor<Object> visitor) {
        Object result1 = visitor.visit(this.first);

        Object result2 = visitor.visit(this.index);

        return get(result1, result2);
    }

    public static Object get(Object object, Object index) {
        int i = 0;
        if (index instanceof Number) {
            i = ((Number) index).intValue();
        }

        if (i < 0) {
            throw new TierRunException("索引下标不能为负数:" + i);
        }

        if (object instanceof Map<?, ?>) {
            Map<?, ?> map = (Map<?, ?>) object;
            String key = index.toString();
            return map.get(key);
        } else if (object instanceof List<?>) {
            return ((List<?>) object).get(i);
        } else if (object instanceof Object[]) {
            return ((Object[]) object)[i];
        } else if (object instanceof Iterator<?>) {
            Iterator<?> it = (Iterator<?>) object;
            while (it.hasNext()) {
                i--;
                if (i == -1) {
                    return it.next();
                } else {
                    it.next();
                }
            }
            throw new TierRunException("Entry does not exist: " + i);
        } else if (object instanceof Collection<?>) {
            Iterator<?> iterator = ((Collection<?>) object).iterator();
            return get(iterator, i);
        } else if (object instanceof Enumeration<?>) {
            Enumeration<?> it = (Enumeration<?>) object;
            while (it.hasMoreElements()) {
                i--;
                if (i == -1) {
                    return it.nextElement();
                } else {
                    it.nextElement();
                }
            }
            throw new TierRunException("Entry does not exist: " + i);
        } else if (object == null) {
            throw new TierRunException("Unsupported object type: null");
        } else {
            try {
                return Array.get(object, i);
            } catch (IllegalArgumentException e) {
                throw new TierRunException("不支持的对象类型: " + object.getClass().getName());
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new TierRunException("索引下标越界: " + i);
            }
        }
    }
}
