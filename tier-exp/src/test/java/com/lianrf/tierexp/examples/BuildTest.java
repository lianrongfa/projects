package com.lianrf.tierexp.examples;

import com.lianrf.tierexp.TierExpEngineImpl;
import com.lianrf.tierexp.parser.TierExpLexer;
import com.lianrf.tierexp.parser.TierExpParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;

/**
 * TODO
 *
 * @author lianrf
 * @version 1.0
 * @since 2022/2/24 10:28 上午
 */
public class BuildTest {

    public static void main(String[] args) {
        TierExpEngineImpl engine = new TierExpEngineImpl();

//        InstructionNode parse = engine.parse("(a+b+c)/2");
        Object run = engine.run("(1+2+3)+2");
        System.out.println(run);

//        System.out.println(parse);
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
