package com.lianrf.tierexp.instruction.op;

import com.lianrf.tierexp.context.ExpContext;
import com.lianrf.tierexp.parser.TierExpVisitor;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Operator基类
 *
 * @author lianrf
 * @version 1.0
 * @since 2022/3/11 3:08 下午
 */
public abstract class OperatorAbs implements Operator {

    protected String name;

    protected String aliasName;

    /**
     * 是否需要高精度计算
     */
    protected boolean isPrecise;

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Object call(ExpContext context, ParseTree node, TierExpVisitor<Object> visitor) {

        List<Object> list = new ArrayList<>();
        int count = node.getChildCount();
        for (int i = 0; i < count; i++) {
            ParseTree tree = node.getChild(i);
            if (!(tree instanceof TerminalNode)) {
                Object o = visitor.visit(tree);
                list.add(o);
            }
        }

        return call(list.toArray());
    }

    @Override
    public Class<? extends ParseTree> getTreeClass() {
        return null;
    }

    public abstract Object call(Object[] args);

    public boolean isPrecise() {
        return isPrecise;
    }

    public void setPrecise(boolean precise) {
        isPrecise = precise;
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    @Override
    public void check() {

    }
}
