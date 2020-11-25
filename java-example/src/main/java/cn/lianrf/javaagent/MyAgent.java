package cn.lianrf.javaagent;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.instrument.Instrumentation;

/**
 * @version: v1.0
 * @date: 2020/11/23
 * @author: lianrf
 */
public class MyAgent {
    public static void premain(String agentArgs, Instrumentation inst) {
        new AgentBuilder
                .Default()
                .type(ElementMatchers.nameStartsWith("cn.lianrf.javaagent")); // 指定需要拦截的类
                //.transform()
                //.with()
                //.installOn(inst);
    }
}
