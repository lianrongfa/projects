package cn.lianrf.antlr;

import cn.lianrf.antlr.out.expr.ExprBaseVisitor;
import cn.lianrf.antlr.out.expr.ExprLexer;
import cn.lianrf.antlr.out.expr.ExprParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.UnbufferedCharStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * TODO
 *
 * @author lianrf
 * @version 1.0
 * @since 2021/10/29 5:01 下午
 */
public class ExprTest {
    public static void main(String[] args) {
        String expr="1+2*3";

        ExprLexer lexer = new ExprLexer(new UnbufferedCharStream(new ByteArrayInputStream(expr.getBytes())));

        CommonTokenStream tokens = new CommonTokenStream(lexer);

        ExprParser parser = new ExprParser(tokens);

        ParseTree tree = parser.prog();

        EvalVisitor visitor = new EvalVisitor();
        visitor.visit(tree);


    }
}
class EvalVisitor extends ExprBaseVisitor<Integer>{


    /**
     * 保存运行中的结果
     */
    Map<String, Integer> memory = new HashMap<>();


    @Override
    public Integer visitAssign(ExprParser.AssignContext ctx) {
        String id = ctx.ID().getText();
        int value = visit(ctx.expr());
        memory.put(id, value);
        return value;
    }

    @Override
    public Integer visitPrintExpr(ExprParser.PrintExprContext ctx) {
        Integer value = visit(ctx.expr());
        System.out.println(value);
        return 0;
    }

    @Override
    public Integer visitInt(ExprParser.IntContext ctx) {
        return Integer.valueOf(ctx.INT().getText());
    }

    @Override
    public Integer visitId(ExprParser.IdContext ctx) {
        String id = ctx.ID().getText();
        if (memory.containsKey(id)) {
            return memory.get(id);
        }
        return 0;
    }

    @Override
    public Integer visitMulDiv(ExprParser.MulDivContext ctx) {
        int left = visit(ctx.expr(0));
        int right = visit(ctx.expr(1));
        if (ctx.op.getType() == ExprParser.MUL) {
            return left * right;
        } else {
            return left / right;
        }
    }

    @Override
    public Integer visitAddSub(ExprParser.AddSubContext ctx) {
        int left = visit(ctx.expr(0));
        int right = visit(ctx.expr(1));
        if (ctx.op.getType() == ExprParser.Add) {
            return left + right;
        } else {
            return left - right;
        }
    }

    @Override
    public Integer visitParens(ExprParser.ParensContext ctx) {
        return visit(ctx.expr());
    }

}