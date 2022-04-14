package com.lianrf.tierexp.examples;

import cn.hutool.core.date.StopWatch;
import com.lianrf.tierexp.InstructionNode;
import com.lianrf.tierexp.TierExpEngineImpl;
import com.lianrf.tierexp.context.MapContext;
import com.lianrf.tierexp.parser.TierExpLexer;
import com.lianrf.tierexp.parser.TierExpParser;
import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;
import com.ql.util.express.InstructionSet;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;

import java.util.Random;

/**
 * TODO
 *
 * @author lianrf
 * @version 1.0
 * @since 2022/2/24 10:28 上午
 */
public class BuildTest {

    public static void main(String[] args) throws Exception {

//        System.out.println("_8d19dd61161741e49b33103dfd68d0cb".length());
        String exp = "a+b-c*d/b";

        TierExpEngineImpl engine = new TierExpEngineImpl();
        InstructionNode instructionNode = engine.parse(exp);
        ExpressRunner runner = new ExpressRunner(true, false);
        InstructionSet instructionSet = runner.parseInstructionSet(exp);

        double a = 3, b = 4, c = 5.35, d = 5.318;

        StopWatch stopWatch = new StopWatch("statistics");
        MapContext map = new MapContext();
        DefaultContext<String, Object> context = new DefaultContext<String, Object>();

        Random random = new Random(50);




        int size = 100;





        stopWatch.start("ql-express");
        for (int i = 0; i < size; i++) {

            double v = random.nextDouble();
            a = a + v;
            b = b + v;
            c = c + v;
            d = d + v;
            ql(runner, instructionSet, a, b, c, d, context);
        }
        stopWatch.stop();


        stopWatch.start("tier-exp");
        for (int i = 0; i < size; i++) {
            double v = random.nextDouble();
            a = a + v;
            b = b + v;
            c = c + v;
            d = d + v;
            tier(engine, instructionNode, a, b, c, d, map);
        }
        stopWatch.stop();

        System.out.println(stopWatch.prettyPrint());

    }

    private static void tier(TierExpEngineImpl engine, InstructionNode instructionNode, double a, double b, double c, double d, MapContext map) {
        map.put("a", a);
        map.put("b", b);
        map.put("c", c);
        map.put("d", d);
        engine.run(instructionNode, map);
    }

    private static void ql(ExpressRunner runner, InstructionSet instructionSet, double a, double b, double c, double d, DefaultContext<String, Object> context) throws Exception {
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
