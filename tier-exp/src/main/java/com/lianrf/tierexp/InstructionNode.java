package com.lianrf.tierexp;

import com.lianrf.tierexp.context.ExpContext;
import com.lianrf.tierexp.exception.TierRunException;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

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

    protected Object accept(ExpContext<String, Object> ctx, TierExpEngineImpl engine) {
        ParseTreeVisitor<Object> visitor = ctx.getVisitor();
        if (visitor == null) {
            throw new TierRunException("缺少遍历器");
        }
        ctx.setEngine(engine);
        Object result;
        try {
            result = visitor.visit(tree);
        } catch (Exception e) {
            throw new TierRunException(e.getMessage(), e);
        }
        return result;
    }


    public ParseTree getTree() {
        return tree;
    }

    public void setTree(ParseTree tree) {
        this.tree = tree;
    }
}
