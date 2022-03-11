package com.lianrf.tierexp;

import com.lianrf.tierexp.context.ExpContext;
import com.lianrf.tierexp.parser.TierExpVisitor;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 * InstructionNode
 *
 * @author lianrf
 * @version 1.0
 * @since 2022/3/2 10:56 上午
 */
public class InstructionNode {

    private ParseTree tree;

    public InstructionNode(ParseTree tree) {
        this.tree = tree;
    }

    public Object accept(TierExpVisitor<?> visitor, ExpContext ctx) {
        return visitor.visit(tree);
    }


    public ParseTree getTree() {
        return tree;
    }

    public void setTree(ParseTree tree) {
        this.tree = tree;
    }
}
