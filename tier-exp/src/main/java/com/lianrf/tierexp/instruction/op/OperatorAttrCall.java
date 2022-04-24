package com.lianrf.tierexp.instruction.op;

import com.lianrf.tierexp.common.ExceptionUtil;
import com.lianrf.tierexp.common.ReflectUtil;
import com.lianrf.tierexp.context.ExpContext;
import com.lianrf.tierexp.exception.TierRunException;
import com.lianrf.tierexp.parser.TierExpVisitor;
import org.antlr.v4.runtime.tree.ParseTree;

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
public class OperatorAttrCall extends OperatorCommonCall {


    public OperatorAttrCall(String name) {
        this.name = name;
    }


    @Override
    public Object call(ExpContext<String, Object> context, ParseTree node, TierExpVisitor<Object> visitor) {

        ParseTree markTree = node.getChild(0);

        Object childResult = visitor.visit(markTree.getChild(0));
        String childName = markTree.getChild(2).getText();

        Object[] params = getObjects(node, visitor);

        Class<?> aClass = ReflectUtil.getClass(childResult);

        Class<?>[] objClazz = new Class[params.length];
        for (int i = 0; i < params.length; i++) {
            objClazz[i]= ReflectUtil.getClass(params[i]);
        }

        Object target=childResult;
        Method method;
        try {
            method = aClass.getMethod(childName, objClazz);
        } catch (NoSuchMethodException e) {
            throw new TierRunException();
        }

        if (Modifier.isStatic(method.getModifiers())) {
            target=null;
        }

        try {
            method.setAccessible(true);
            return method.invoke(target, params);
        } catch (IllegalAccessException e) {
            throw new TierRunException(String.format("无权限执行%s", node.getText()));
        } catch (InvocationTargetException e) {
           throw new TierRunException(ExceptionUtil.unwrapThrowable(e));
        }
    }


    @Override
    public Object call(Object[] list) {
        return null;
    }
}
