package com.lianrf.tierexp.interpreter;


import com.lianrf.tierexp.common.OperatorNumber;
import com.lianrf.tierexp.context.ExpContext;
import com.lianrf.tierexp.parser.Configuration;
import com.lianrf.tierexp.parser.TierExpParser;
import com.lianrf.tierexp.parser.TierExpVisitor;
import org.antlr.v4.runtime.tree.ParseTree;

public class ConstInterpreter implements Interpreter {

    private Object value;

    public ConstInterpreter() {
    }

    public ConstInterpreter(ParseTree node) {
        ParseTree child = node.getChild(0);

        String text = child.getText();

        Configuration cfg = new Configuration();

        if (child instanceof TierExpParser.NumContext) {
            if (cfg.isPrecise()) {
                this.value = OperatorNumber.parseBigDecimal(text);
            } else {
                this.value = OperatorNumber.parse(text);
            }
        } else {
            this.value = text;
        }
    }

    public ConstInterpreter(ExpContext context, ParseTree node) {

    }

    @Override
    public Object interpret(ExpContext context, ParseTree node, TierExpVisitor<Object> visitor) {
        return value;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
