package cn.lianrf.antlr.out.expr;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 * TODO
 *
 * @author lianrf
 * @version 1.0
 * @since 2021/11/16 7:20 下午
 */
public class ExprTest {
    public static void main(String[] args) {
        ANTLRInputStream input = new ANTLRInputStream("1\n" +
                "2+3\n" +
                "a=1\n" +
                "(1+2)*3-9/3+(9-1)/2\n");
        ExprLexer lexer = new ExprLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ExprParser parser = new ExprParser(tokens);
        ParseTree tree = parser.prog(); // 从prog规则开始进行语法分析
        System.out.println(tree.toStringTree(parser)); //
    }
}
