package cn.lianrf.antlr.code.rows;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;
import java.io.InputStream;

/**
 * TODO
 *
 * @author lianrf
 * @version 1.0
 * @since 2021/11/19 5:15 下午
 */
public class RowsTest {
    public static void main(String[] args) throws IOException {

        InputStream resourceAsStream = RowsTest.class.getResourceAsStream("/t.rows");
        CharStream charStream = CharStreams.fromStream(resourceAsStream);


        RowsLexer lexer = new RowsLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        int col = 2;
        RowsParser parser = new RowsParser(tokens, col); // 传递列号作为参数
        parser.setBuildParseTree(false); // 不需要浪费时间建立语法分析树
        parser.file(); // 开始语法分析
    }
}
