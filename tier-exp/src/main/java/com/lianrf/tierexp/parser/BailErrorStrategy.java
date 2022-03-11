package com.lianrf.tierexp.parser;

import org.antlr.v4.runtime.DefaultErrorStrategy;
import org.antlr.v4.runtime.InputMismatchException;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Token;

import java.util.HashSet;
import java.util.Set;

/**
 * @author lianrf
 * @version 1.0
 * @since 2022/2/24 4:29 下午
 */
public class BailErrorStrategy extends DefaultErrorStrategy {

    private final Set<Token> set = new HashSet<>();

    @Override
    public void recover(Parser recognizer, RecognitionException e) {
        throw new RuntimeException(e);
    }


    @Override
    public Token recoverInline(Parser recognizer)
            throws RecognitionException {
        throw new RuntimeException(new InputMismatchException(recognizer));
    }

    /**
     * Make sure we don't attempt to recover from problems in subrules.
     */
    @Override
    public void sync(Parser recognizer) {
        /*String text = recognizer.getCurrentToken().getText();
        if (set.contains(text)) {
            set.remove(text);
        } else {
            set.add(text);
        }*/
        System.out.println(recognizer.getCurrentToken().getText());
    }

    public boolean hasToken() {
        return !set.isEmpty();
    }

    public Set getSet() {
        return set;
    }
}
