package com.lianrf.tierexp.instruction.op;

import com.lianrf.tierexp.common.ExceptionUtil;
import com.lianrf.tierexp.common.ReflectUtil;
import com.lianrf.tierexp.context.ExpContext;
import com.lianrf.tierexp.exception.TierRunException;
import com.lianrf.tierexp.parser.TierExpParser;
import com.lianrf.tierexp.parser.TierExpVisitor;
import org.antlr.v4.runtime.tree.ParseTree;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * attr
 *
 * @author lianrf
 * @version 1.0
 * @since 2022/3/11 5:42 下午
 */
public class OperatorDot extends OperatorAbs {

    private static final String ATTR_ERROR_TEMPLATE = "无法从%s获取到属性%s";

    public OperatorDot(String name) {
        this.name = name;
    }


    @Override
    public Object call(ExpContext<String, Object> context, ParseTree node, TierExpVisitor<Object> visitor) {
        Object result = null;

        ParseTree markTree = node.getChild(0);
        Object left = visitor.visit(markTree);
        Class<?> lClazz = ReflectUtil.getClass(left);

        String targetName = node.getChild(2).getText();
        ParseTree parent = node.getParent();

        if(parent instanceof TierExpParser.ExprCallContext){
            throw new TierRunException("未知的DOT操作,exp:" + parent.getText());
        }

        return getAttr(markTree, left, lClazz, targetName);
    }

    private Object getAttr(ParseTree markTree, Object left, Class<?> lClazz, String targetName) {
        Object result;
        Method method = ReflectUtil.findMethodByGet(lClazz, targetName);

        if (method == null) {
            try {
                Object target = left;
                Field field = lClazz.getField(targetName);
                if (Modifier.isStatic(field.getModifiers())) {
                    target = null;
                }
                field.setAccessible(true);
                result = field.get(target);
            } catch (NoSuchFieldException e) {
                throw new TierRunException(String.format(ATTR_ERROR_TEMPLATE, markTree.getText(), targetName));
            } catch (IllegalAccessException e) {
                throw new TierRunException(String.format("无权限从%s获取到属性%s", markTree.getText(), targetName));
            }
        } else {
            Object target = left;
            if (Modifier.isStatic(method.getModifiers())) {
                target = null;
            }
            try {
                method.setAccessible(true);
                result = method.invoke(target);
            } catch (IllegalAccessException | IllegalArgumentException e) {
                throw new TierRunException(String.format(ATTR_ERROR_TEMPLATE, markTree.getText(), targetName));
            } catch (InvocationTargetException e) {
                throw new TierRunException(String.format("%s执行%s错误", markTree.getText(), targetName), ExceptionUtil.unwrapThrowable(e));
            }
        }
        return result;
    }

    @Override
    public Object call(Object[] list) {
        return null;
    }
}
