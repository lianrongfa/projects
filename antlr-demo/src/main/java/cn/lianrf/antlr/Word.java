package cn.lianrf.antlr;

import lombok.Data;

/**
 *
 * @author lianrf
 * @version 1.0
 * @since 2021/11/1 11:01 上午
 */
@Data
public class Word {
    private String aWord;
    private int line;
    private int col;
    private int index;

    public Word(String word,int aLine,int aCol) {
        this.aWord = word;
        this.line = aLine;
        this.col = aCol - word.length() + 1;
    }

    @Override
    public String toString() {
        return this.aWord;
    }
}
