package cn.lianrf.antlr.code.agile.parser;

import cn.lianrf.antlr.code.agile.context.ExpContext;

/**
 * Expression
 *
 * @author lianrf
 * @version 1.0
 * @since 2022/2/21 5:59 下午
 */
public interface Expression {

    /**
     * 求表达式的值
     *
     * @param context context
     * @return value
     */
    Object eval(ExpContext context);

}
