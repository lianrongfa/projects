package com.lianrf.tierexp.instruction;

import com.lianrf.tierexp.exception.TierParseException;
import com.lianrf.tierexp.instruction.op.Operator;
import com.lianrf.tierexp.instruction.op.OperatorAdd;
import com.lianrf.tierexp.instruction.op.OperatorAttrCall;
import com.lianrf.tierexp.instruction.op.OperatorDivide;
import com.lianrf.tierexp.instruction.op.OperatorDot;
import com.lianrf.tierexp.instruction.op.OperatorMod;
import com.lianrf.tierexp.instruction.op.OperatorMultiply;
import com.lianrf.tierexp.instruction.op.OperatorNot;
import com.lianrf.tierexp.instruction.op.OperatorSub;
import com.lianrf.tierexp.parser.Configuration;

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

    public static String SYSTEM_PREFIX = "&";

    protected Configuration configuration;

    /**
     * 共用operator集合
     */
    private final Map<String, Operator> operator = new HashMap<>();

    /**
     * 用户operator集合
     */
    private final Map<String, Operator> userOperator = new HashMap<>();

    public OperatorManager(Configuration configuration) {

        this.configuration = configuration;

        for (Item item : BASE_ITEM) {
            addOperatorDefault(item.name, item.op.apply(item.name));
        }
    }

    private void addOperatorDefault(String name, Operator op) {
        Operator oldOp = this.operator.get(name);
        if (oldOp != null) {
            throw new TierParseException("重复定义操作符：" + name + "old:"
                    + oldOp.getClass() + " new:" + op.getClass());
        }
        op.setPrecise(configuration.isPrecise());
        op.setAliasName(name);
        operator.put(name, op);
    }

    public Operator replaceOperator(String name, Operator op) {
        Operator old = this.userOperator.remove(name);
        this.addOperator(name, op);
        return old;
    }

    public void addOperator(String name, Operator op) {
        Operator oldOp = this.userOperator.get(name);
        if (oldOp != null) {
            throw new TierParseException("重复定义操作符：" + name + "old:"
                    + oldOp.getClass() + " new:" + op.getClass());
        }
        op.setPrecise(configuration.isPrecise());
        op.setAliasName(name);
        userOperator.put(name, op);
    }

    public Operator getOperator(String opName) {
        Operator op = this.userOperator.get(opName);
        if (op == null) {
            return this.operator.get(opName);
        }
        return op;
    }


    private static final Item[] BASE_ITEM;

    static {
        BASE_ITEM = new Item[]{
                new Item("+", OperatorAdd::new),
                new Item("-", OperatorSub::new),
                new Item("*", OperatorMultiply::new),
                new Item("/", OperatorDivide::new),
                new Item(".", OperatorDot::new),
                new Item("&ExprAttrContext", OperatorAttrCall::new),
                new Item("!", OperatorNot::new),
                new Item("mod", OperatorMod::new),
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
