package com.lianrf.tierexp.examples;

import com.greenpineyu.fel.Expression;
import com.greenpineyu.fel.FelEngine;
import com.lianrf.tierexp.InstructionNode;
import com.lianrf.tierexp.TierExpEngineImpl;
import com.lianrf.tierexp.context.ExpContext;
import com.lianrf.tierexp.context.DefaultContext;
import com.ql.util.express.ExpressRunner;
import com.ql.util.express.InstructionSet;

import java.util.Random;
import java.util.Scanner;

/**
 * TierTest
 *
 * @author lianrf
 * @version 1.0
 * @since 2022/4/14 4:47 下午
 */
public class TierTest {
    public static void main(String[] args) throws Exception {

        String exp = "a-b*c+d-c-d-a/b*c+a-d";
        TierExpEngineImpl engine = new TierExpEngineImpl(false);
        InstructionNode instructionNode = engine.parse(exp);


        ExpressRunner runner = new ExpressRunner(false, false);
        InstructionSet instructionSet = runner.parseInstructionSet(exp);



        com.greenpineyu.fel.context.MapContext felContext = new com.greenpineyu.fel.context.MapContext();
        com.ql.util.express.DefaultContext context = new com.ql.util.express.DefaultContext();
        felContext.set("a", 1.3);
        felContext.set("b", 2.2);
        felContext.set("c", 3.1);
        felContext.set("d", 3.4);
        ExpContext map=new DefaultContext();

        Expression expression = FelEngine.instance.compile(exp, felContext);

        Random random = new Random(50);
        double a = 3, b = 4, c = 5.35, d = 5.318;

        Scanner scanner = new Scanner(System.in);
        for (;;) {
            scanner.nextLine();
            double v = random.nextDouble();
            a = a + v;
            b = b + v;
            c = c + v;
            d = d + v;
            extracted(engine, instructionNode, runner, instructionSet, expression, felContext, context, map, a, b, c, d);
        }
    }

    private static void extracted(TierExpEngineImpl engine, InstructionNode instructionNode, ExpressRunner runner, InstructionSet instructionSet, Expression felNode, com.greenpineyu.fel.context.MapContext felContext, com.ql.util.express.DefaultContext context, ExpContext map, double a, double b, double c, double d) throws Exception {
        ql(runner, instructionSet, a, b, c, d, context);
        tier(engine, instructionNode, a, b, c, d, map);
        fel(felNode, a, b, c, d, felContext);
    }

    private static void tier(TierExpEngineImpl engine, InstructionNode instructionNode, double a, double b, double c, double d, ExpContext map) {
        map.put("a", a);
        map.put("b", b);
        map.put("c", c);
        map.put("d", d);
        System.out.println(engine.run(instructionNode, map));
    }

    private static void ql(ExpressRunner runner, InstructionSet instructionSet, double a, double b, double c, double d, com.ql.util.express.DefaultContext context) throws Exception {
        context.put("a", a);
        context.put("b", b);
        context.put("c", c);
        context.put("d", d);
        System.out.println(runner.execute(instructionSet, context, null, false, false, null));
    }

    private static void fel(Expression felNode, double a, double b, double c, double d, com.greenpineyu.fel.context.MapContext felContext) {
        felContext.set("a", a);
        felContext.set("b", b);
        felContext.set("c", c);
        felContext.set("d", d);

        System.out.println(felNode.eval(felContext));

    }
}
