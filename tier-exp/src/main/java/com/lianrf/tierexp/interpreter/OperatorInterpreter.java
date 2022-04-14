package com.lianrf.tierexp.interpreter;

import com.lianrf.tierexp.RunEnvironment;
import com.lianrf.tierexp.context.ExpContext;
import com.lianrf.tierexp.exception.TierRunException;
import com.lianrf.tierexp.instruction.OperatorManager;
import com.lianrf.tierexp.instruction.op.Operator;
import com.lianrf.tierexp.parser.TierExpVisitor;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 * 操作符解释器
 *
 * @author lianrf
 * @version 1.0
 * @since 2022/3/11 2:47 下午
 */
public class OperatorInterpreter implements Interpreter {

    private Object opO;

    private ParseTree node;

    public OperatorInterpreter(Object opO, ParseTree node) {
        this.opO = opO;
        this.node = node;
    }

    @Override
    public Object interpret(ExpContext context, ParseTree node, TierExpVisitor<Object> visitor) {
        RunEnvironment environment = RunEnvironment.get();
        OperatorManager manager = environment.getEngine().getOpManager();

        String name = getName(this.opO);

        Operator operator = manager.getOperator(name);

        return operator.call(context, node, visitor);
    }


    private String getName(Object opO) {
        String name;
        if (opO instanceof ParserRuleContext) {
            //todo 类方法调用
            throw new TierRunException("类方法调用尚未实现");
        } else if (opO instanceof Token) {
            name = ((Token) opO).getText();
        } else {
            throw new TierRunException("未知的方法调用" + opO);
        }
        return name;
    }

    public Object getOpO() {
        return opO;
    }

    public void setOpO(Object opO) {
        this.opO = opO;
    }

    public ParseTree getNode() {
        return node;
    }

    public void setNode(ParseTree node) {
        this.node = node;
    }
}
