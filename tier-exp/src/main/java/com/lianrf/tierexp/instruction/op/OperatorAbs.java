package com.lianrf.tierexp.instruction.op;

import com.lianrf.tierexp.common.ArrayUtil;
import com.lianrf.tierexp.context.ExpContext;
import com.lianrf.tierexp.parser.TierExpVisitor;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.Arrays;

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
    public Object call(ExpContext<String, Object> context, ParseTree node, TierExpVisitor<Object> visitor) {
        return call(getObjects(node, visitor));
    }

    protected Object[] getObjects(ParseTree node, TierExpVisitor<Object> visitor) {
        int count = node.getChildCount();
        int idx = 0;

        int size = count - 1;
        if(size<=0){
            return ArrayUtil.EMP_ARR;
        }

        Object[] objects = new Object[size];
        for (int i = 0; i < count; i++) {
            ParseTree tree = node.getChild(i);
            if (!(tree instanceof TerminalNode)) {
                Object o = visitor.visit(tree);
                objects[idx] = o;
                idx++;
            }
        }
        if(idx==size){
            return objects;
        }
        return Arrays.copyOfRange(objects, 0, idx);
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
