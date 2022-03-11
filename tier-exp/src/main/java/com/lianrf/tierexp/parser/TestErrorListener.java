package com.lianrf.tierexp.parser;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.Token;

/**
 * ErrorListener
 *
 * @author lianrf
 * @version 1.0
 * @since 2022/3/1 2:58 下午
 */
public class TestErrorListener extends BaseErrorListener {

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {

        /*Parser parser = (Parser) recognizer;
        List<String> stack = parser.getRuleInvocationStack();
        Token currentToken = parser.getCurrentToken();
        Collections.reverse(stack);
        System.out.println(currentToken);
        System.out.println("stack = " + stack+" msg = "+msg);*/


        System.err.println("line " + line + ":" + charPositionInLine + " " + msg);
        underlineError(recognizer, (Token) offendingSymbol,
                line, charPositionInLine);
    }

    protected void underlineError(Recognizer recognizer,
                                  Token offendingToken, int line,
                                  int charPositionInLine) {
        CommonTokenStream tokens =
                (CommonTokenStream) recognizer.getInputStream();
        String input = tokens.getTokenSource().getInputStream().toString();
        String[] lines = input.split("\n");
        String errorLine = lines[line - 1];
        System.err.println(errorLine);
        for (int i = 0; i < charPositionInLine; i++) System.err.print(" ");
        int start = offendingToken.getStartIndex();
        int stop = offendingToken.getStopIndex();
        if (start >= 0 && stop >= 0) {
            for (int i = start; i <= stop; i++) System.err.print("^");
        }
        System.err.println();
    }
}
