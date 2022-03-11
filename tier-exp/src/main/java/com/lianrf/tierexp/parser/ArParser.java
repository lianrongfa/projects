package com.lianrf.tierexp.parser;

import com.lianrf.tierexp.InstructionNode;
import com.lianrf.tierexp.TierExpEngine;
import com.lianrf.tierexp.exception.ParseException;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;

/**
 * TODO
 *
 * @author lianrf
 * @version 1.0
 * @since 2022/2/24 3:07 下午
 */
public class ArParser implements IParser {

    private Configuration configuration;

    private TierExpEngine engine;

    public ArParser(Configuration configuration, TierExpEngine engine) {
        this.configuration = configuration;
        this.engine = engine;
    }

    @Override
    public InstructionNode parse(String exp) {
        CodePointCharStream charStream = CharStreams.fromString(exp);
        TestErrorListener errorListener = new TestErrorListener();

        TierExpLexer lexer = new TierExpLexer(charStream);

//        lexer.addErrorListener(errorListener);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);

        TierExpParser expParser = new TierExpParser(tokenStream);

        BailErrorStrategy errorStrategy = new BailErrorStrategy();

//        expParser.removeErrorListeners();
//        expParser.addErrorListener(new DiagnosticErrorListener());
//        expParser.addErrorListener(errorListener);
//        expParser.getInterpreter().setPredictionMode(PredictionMode.LL_EXACT_AMBIG_DETECTION);
//        expParser.setErrorHandler(errorStrategy);

        TierExpParser.FileContext tree;
        try {
            tree = expParser.file();
        } catch (RecognitionException e) {
            throw new ParseException(e.getMessage(), e);
        }

        if (errorStrategy.hasToken()) {
            System.out.println(String.join("",errorStrategy.getSet()));
        }


//        System.out.println(tree.toStringTree(expParser));
        return new InstructionNode(tree);
    }
}
