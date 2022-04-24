package com.lianrf.tierexp.instruction.op;

import com.lianrf.tierexp.common.ArrayUtil;
import com.lianrf.tierexp.exception.TierRunException;
import com.lianrf.tierexp.parser.TierExpVisitor;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 * 自定义函数继承基类
 *
 * @author lianrf
 * @version 1.0
 * @since 2022/4/22 3:20 下午
 */
public abstract class OperatorCommonCall extends OperatorAbs {

    @Override
    protected Object[] getObjects(ParseTree node, TierExpVisitor<Object> visitor) {
        int count = node.getChildCount();
        if (count < 3) {
            throw new TierRunException("call参数个数错误");
        }
        if (count == 3) {
            return ArrayUtil.EMP_ARR;
        }
        ParseTree child = node.getChild(2);
        int paramCount = child.getChildCount();
        int idx = 0;

        Object[] paramObj = new Object[paramCount / 2 + 1];
        for (int i = 0; i < paramCount; i = i + 2, idx++) {
            Object o = visitor.visit(child.getChild(i));
            paramObj[idx] = o;
        }
        return paramObj;
    }

}
