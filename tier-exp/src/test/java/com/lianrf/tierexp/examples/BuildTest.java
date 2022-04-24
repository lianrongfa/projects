package com.lianrf.tierexp.examples;

import com.greenpineyu.fel.FelEngine;
import com.greenpineyu.fel.parser.FelNode;
import com.lianrf.tierexp.InstructionNode;
import com.lianrf.tierexp.TierExpEngineImpl;
import com.lianrf.tierexp.context.DefaultContext;
import com.lianrf.tierexp.parser.TierExpLexer;
import com.lianrf.tierexp.parser.TierExpParser;
import com.ql.util.express.ExpressRunner;
import com.ql.util.express.InstructionSet;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;

import java.util.Random;

/**
 * test
 *
 * @author lianrf
 * @version 1.0
 * @since 2022/2/24 10:28 上午
 */
public class BuildTest {

    public static void main(String[] args) throws Exception {

//        System.out.println("_8d19dd61161741e49b33103dfd68d0cb".length());
        String exp = "(a+b)*c+d+a+b+c-a*c+a+c+d*c/a+d";

        TierExpEngineImpl engine = new TierExpEngineImpl(false);
        InstructionNode instructionNode = engine.parse(exp);

        ExpressRunner runner = new ExpressRunner(false, false);
        InstructionSet instructionSet = runner.parseInstructionSet(exp);

        FelNode felNode = FelEngine.instance.parse(exp);


        double a = 3, b = 4, c = 5.35, d = 5.318;

        DefaultContext map = new DefaultContext();
        com.ql.util.express.DefaultContext context = new com.ql.util.express.DefaultContext();

        com.greenpineyu.fel.context.MapContext felContext = new com.greenpineyu.fel.context.MapContext();

        Random random = new Random(50);

        int size = 100000;

        long l = System.nanoTime();

        for (int i = 0; i < size; i++) {

            double v = random.nextDouble();
            a = a + v;
            b = b + v;
            c = c + v;
            d = d + v;
            fel(FelEngine.instance, felNode, a, b, c, d, felContext);
        }
        System.out.println("f avg:"+(System.nanoTime()-l)/size);

        long m = System.nanoTime();
        for (int i = 0; i < size; i++) {
            double v = random.nextDouble();
            a = a + v;
            b = b + v;
            c = c + v;
            d = d + v;
            tier(engine, instructionNode, a, b, c, d, map);
        }
        System.out.println("t avg:"+(System.nanoTime()-m)/size);

        long n = System.nanoTime();
        for (int i = 0; i < size; i++) {

            double v = random.nextDouble();
            a = a + v;
            b = b + v;
            c = c + v;
            d = d + v;
            ql(runner, instructionSet, a, b, c, d, context);
        }
        System.out.println("q avg:"+(System.nanoTime()-n)/size);


        long x = System.nanoTime();
        for (int i = 0; i < size; i++) {

            double v = random.nextDouble();
            a = a + v;
            b = b + v;
            c = c + v;
            d = d + v;

            double v1 = a + b - c * d / b;
        }

        System.out.println("s avg:"+(System.nanoTime()-x)/size);


    }

    private static void fel(FelEngine instance, FelNode felNode, double a, double b, double c, double d, com.greenpineyu.fel.context.MapContext felContext) {
        felContext.set("a", a);
        felContext.set("b", b);
        felContext.set("c", c);
        felContext.set("d", d);

        felNode.eval(felContext);

    }


    private static void tier(TierExpEngineImpl engine, InstructionNode instructionNode, double a, double b, double c, double d, DefaultContext map) {
        map.put("a", a);
        map.put("b", b);
        map.put("c", c);
        map.put("d", d);
        engine.run(instructionNode, map);
    }

    private static void ql(ExpressRunner runner, InstructionSet instructionSet, double a, double b, double c, double d, com.ql.util.express.DefaultContext context) throws Exception {
        context.put("a", a);
        context.put("b", b);
        context.put("c", c);
        context.put("d", d);
        runner.execute(instructionSet, context, null, false, false, null);
    }

    private static void old() {
        CodePointCharStream charStream = CharStreams.fromString("$(abc).m()");

        TierExpLexer lexer = new TierExpLexer(charStream);

        CommonTokenStream tokenStream = new CommonTokenStream(lexer);

        TierExpParser expParser = new TierExpParser(tokenStream);

        TierExpParser.FileContext tree = expParser.file();


        String s = tree.toStringTree(expParser);

        System.out.println(s);
    }
}
