package cn.lianrf.antlr.code.lexpr;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 * TODO
 *
 * @author lianrf
 * @version 1.0
 * @since 2021/11/30 5:41 下午
 */
public class TestLEvalVisitor {
    // a4 -visitor Expr.g4
    /** Visitor "calculator" */
    public static class EvalVisitor extends LExprBaseVisitor<Integer> {
        public Integer visitMult(LExprParser.MultContext ctx) {
            return visit(ctx.e(0)) * visit(ctx.e(1));
        }

        public Integer visitAdd(LExprParser.AddContext ctx) {
            return visit(ctx.e(0)) + visit(ctx.e(1));
        }

        public Integer visitInt(LExprParser.IntContext ctx) {
            return Integer.valueOf(ctx.INT().getText());
        }
    }

    public static void main(String[] args) throws Exception {


        CodePointCharStream stream = CharStreams.fromString("1+2+3");

        LExprLexer lexer = new LExprLexer(stream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        LExprParser parser = new LExprParser(tokens);
        parser.setBuildParseTree(true);      // 一般不用设置 因为默认为true 设置为需要生成语法树
        ParseTree tree = parser.s(); // parse
        // show tree in text form
        System.out.println(tree.toStringTree(parser));

        EvalVisitor evalVisitor = new EvalVisitor();
        int result = evalVisitor.visit(tree);
        System.out.println("visitor result = "+result);
    }
}
