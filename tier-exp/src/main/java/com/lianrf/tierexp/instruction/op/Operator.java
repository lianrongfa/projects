package com.lianrf.tierexp.instruction.op;

import com.lianrf.tierexp.context.ExpContext;
import com.lianrf.tierexp.parser.TierExpVisitor;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 * Operator
 *
 * @author lianrf
 * @version 1.0
 * @since 2022/3/2 10:57 上午
 */
public interface Operator {


    /**
     * 获得Operator名称
     *
     * @return 名称
     */
    String getName();


    Object call(ExpContext context, ParseTree node, TierExpVisitor<Object> visitor);


    /**
     * 获取解析树class
     * @return class
     */
    Class<? extends ParseTree> getTreeClass();
}
