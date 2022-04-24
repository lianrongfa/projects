package com.lianrf.tierexp.instruction.op;

import com.lianrf.tierexp.exception.TierRunException;

/**
 * !expr
 *
 * @author lianrf
 * @version 1.0
 * @since 2022/4/24 3:01 下午
 */
public class OperatorNot extends OperatorAbs {

    public OperatorNot(String name) {
        this.name = name;
    }

    @Override
    public Object call(Object[] list) {
        return executeInner(list[0]);
    }

    public Object executeInner(Object op) {
        Object result;
        if (op == null) {
            throw new TierRunException("null不能执行操作：" + this.getName());
        }
        if (Boolean.class.equals(op.getClass())) {
            result = !(Boolean) op;
        } else {
            String msg = "未定义类型%s的%s操作";
            throw new TierRunException(String.format(msg, op.getClass().getName(), this.name));
        }
        return result;
    }
}
