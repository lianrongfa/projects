package com.lianrf.tierexp.instruction;

import com.lianrf.tierexp.exception.TierParseException;
import com.lianrf.tierexp.instruction.op.Operator;
import com.lianrf.tierexp.instruction.op.OperatorAdd;
import com.lianrf.tierexp.instruction.op.OperatorDivide;
import com.lianrf.tierexp.instruction.op.OperatorDot;
import com.lianrf.tierexp.instruction.op.OperatorMultiply;
import com.lianrf.tierexp.instruction.op.OperatorSub;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * OperatorManager
 *
 * @author lianrf
 * @version 1.0
 * @since 2022/3/11 10:59 上午
 */
public class OperatorManager {

    /**
     * 是否需要高精度计算
     */
    protected boolean isPrecise;

    /**
     * operator集合
     */
    private final Map<String, Operator> operator = new HashMap<>();

    public OperatorManager(boolean isPrecise) {
        this.isPrecise = isPrecise;
        for (Item item : BASE_ITEM) {
            addOperator(item.name, item.op.apply(item.name));
        }
    }

    public void addOperator(String name, Operator op) {
        Operator oldOp = this.operator.get(name);
        if (oldOp != null) {
            throw new TierParseException("重复定义操作符：" + name + "old:"
                    + oldOp.getClass() + " new:" + op.getClass());
        }
        op.setPrecise(this.isPrecise);
        op.setAliasName(name);
        operator.put(name, op);
    }

    public Operator replaceOperator(String name, Operator op) {
        Operator old = this.operator.remove(name);
        this.addOperator(name, op);
        return old;
    }

    public Operator getOperator(String opName) {
        return this.operator.get(opName);
    }


    private static final Item[] BASE_ITEM;

    static {
        BASE_ITEM = new Item[]{
                new Item("+", OperatorAdd::new),
                new Item("-", OperatorSub::new),
                new Item("*", OperatorMultiply::new),
                new Item("/", OperatorDivide::new),
                new Item(".", OperatorDot::new),
        };
    }

    static class Item {
        public Item(String name, Function<String, Operator> op) {
            this.name = name;
            this.op = op;
        }

        private final String name;
        private final Function<String, Operator> op;
    }
}
