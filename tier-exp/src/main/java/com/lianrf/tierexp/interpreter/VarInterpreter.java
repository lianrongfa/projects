package com.lianrf.tierexp.interpreter;

import com.lianrf.tierexp.context.ExpContext;
import com.lianrf.tierexp.exception.TierRunException;
import com.lianrf.tierexp.parser.TierExpVisitor;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

/**
 * 变量
 *
 * @author lianrf
 * @version 1.0
 * @since 2022/3/7 3:12 下午
 */
public class VarInterpreter implements Interpreter {

    private final String text;

    public VarInterpreter(ParseTree node) {
        ParseTree child = node.getChild(0);
        if (!(child instanceof TerminalNode)) {
            // todo 异常处理
            throw new TierRunException();
        }
        this.text = child.getText();
    }

    @Override
    public Object interpret(ExpContext<String, Object> context, ParseTree node, TierExpVisitor<Object> visitor) {
        if (context == null) {
            throw new TierRunException("ExpContext不能为空");
        }
        return context.get(this.text);
    }
}
