package com.lianrf.tierexp.interpreter.struct;

/**
 * FactoryBuild
 *
 * @author lianrf
 * @version 1.0
 * @since 2022/3/8 9:56 上午
 */
public class InterpreterFactoryBuild {

    public static InterpreterFactory build() {
        return new SimpleInterpreterFactory();
    }

}
