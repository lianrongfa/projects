package com.lianrf.tierexp.interpreter;


import com.lianrf.tierexp.context.ExpContext;
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

        if (child instanceof TierExpParser.NumContext) {


            this.value = Double.valueOf(text);
        }
        if (child instanceof TierExpParser.StrContext) {
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
