package com.lianrf.tierexp;

import com.lianrf.tierexp.context.ExpContext;
import com.lianrf.tierexp.exception.TierParseException;
import com.lianrf.tierexp.exception.TierRunException;

/**
 * 脚本执行引擎
 *
 * @author lianrf
 * @version 1.0
 * @since 2022/2/23 5:37 下午
 */
public interface TierExpEngine {
    /**
     * 计算表达式结果
     *
     * @param exp 表达式
     * @param ctx 引擎上下文
     * @return result
     */
    Object run(String exp, ExpContext<String, Object> ctx) throws TierRunException, TierParseException;


    /**
     * 解析表达式为指令
     *
     * @param exp 表达式
     * @return 指令
     */
    InstructionNode parse(String exp) throws TierParseException;

    /**
     * 计算表达式结果
     *
     * @param instructionNode 指令
     * @param ctx             context
     * @return Object
     */
    Object run(InstructionNode instructionNode, ExpContext<String, Object> ctx) throws TierRunException, TierParseException;
}
