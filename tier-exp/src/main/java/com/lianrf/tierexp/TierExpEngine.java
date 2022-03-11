package com.lianrf.tierexp;

import com.lianrf.tierexp.context.ExpContext;

/**
 * 脚本执行引擎
 *
 * @author lianrf
 * @version 1.0
 * @since 2022/2/23 5:37 下午
 */
public interface TierExpEngine {
    /**
     * 执行表达式，获取结果
     *
     * @param exp 表达式
     * @return result
     */
    Object run(String exp);

    /**
     * 使用指定的引擎上下文执行表达式，获取结果
     *
     * @param exp 表达式
     * @param ctx 引擎上下文
     * @return result
     */
    Object run(String exp, ExpContext ctx);


    InstructionNode parse(String exp);
}
