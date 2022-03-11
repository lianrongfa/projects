package com.lianrf.tierexp.parser;

import com.lianrf.tierexp.InstructionNode;

/**
 * Parser
 *
 * @author lianrf
 * @version 1.0
 * @since 2022/2/24 3:01 下午
 */
public interface IParser {

    InstructionNode parse(String exp);
}
