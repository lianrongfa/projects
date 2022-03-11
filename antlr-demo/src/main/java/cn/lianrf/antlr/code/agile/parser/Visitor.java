package cn.lianrf.antlr.code.agile.parser;

import cn.lianrf.antlr.code.agile.AgileExpBaseVisitor;
import cn.lianrf.antlr.code.agile.AgileExpParser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author lianrf
 * @version 1.0
 * @since 2022/1/30 10:03 上午
 */
public class Visitor extends AgileExpBaseVisitor<Object> {


    @Override
    public Object visitAttr(AgileExpParser.AttrContext ctx) {
        List<ParseTree> nodes = ctx.children;

        ParseTree leftNode = nodes.get(0);

        Object leftR = null;

        if (leftNode instanceof TerminalNode) {

        } else {
            leftR = visit(leftNode);
        }

        ParseTree rightNode = nodes.get(2);

        String text = rightNode.getText();

        Class<?> aClass = (Class<?>) leftR;
        ArrayList<Object> result = new ArrayList<>();
        try {
            Field field = aClass.getField(text);
            result.add(field);
        } catch (NoSuchFieldException e) {

        }
        Method[] methods = aClass.getMethods();

        for (Method method : methods) {
            if (method.getName().equals(text)) {
                result.add(method);
            }
        }

        return result;
    }


}
