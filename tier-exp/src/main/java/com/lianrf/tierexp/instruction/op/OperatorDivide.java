package com.lianrf.tierexp.instruction.op;

import com.lianrf.tierexp.common.OperatorNumber;

/**
 * 乘
 *
 * @author lianrf
 * @version 1.0
 * @since 2022/3/11 5:42 下午
 */
public class OperatorDivide extends OperatorAbs {

    public OperatorDivide(String name) {
        this.name = name;
    }

    @Override
    public Object call(Object[] list) {
        return OperatorNumber.divide(list[0], list[1], this.isPrecise);
    }
}
