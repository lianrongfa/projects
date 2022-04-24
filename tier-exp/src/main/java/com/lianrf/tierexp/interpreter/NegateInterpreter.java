package com.lianrf.tierexp.interpreter;

import com.lianrf.tierexp.context.ExpContext;
import com.lianrf.tierexp.exception.TierRunException;
import com.lianrf.tierexp.parser.TierExpVisitor;
import org.antlr.v4.runtime.tree.ParseTree;

import java.math.BigDecimal;

/**
 * @author lianrf
 * @version 1.0
 * @since 2022/3/7 3:12 下午
 */
public class NegateInterpreter implements Interpreter {

    private final String symbol;

    private final ParseTree node;

    public NegateInterpreter(ParseTree node) {
        this.symbol = node.getChild(0).getText();
        this.node = node.getChild(1);
    }

    @Override
    public Object interpret(ExpContext<String, Object> context, ParseTree node, TierExpVisitor<Object> visitor) {
        Object o = visitor.visit(this.node);

        if (!(o instanceof Number)) {
            throw new TierRunException(String.format("未知的类型%s执行Negate指令", 1));
        }

        if ("-".equals(symbol)) {

            //todo 类型改变了

            BigDecimal decimal = new BigDecimal(o.toString());
            return decimal.negate();
        }
        return o;

    }
}
