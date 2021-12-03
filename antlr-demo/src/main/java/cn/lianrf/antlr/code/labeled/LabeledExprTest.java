package cn.lianrf.antlr.code.labeled;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

/**
 * TODO
 *
 * @author lianrf
 * @version 1.0
 * @since 2021/11/17 11:17 上午
 */
public class LabeledExprTest {
    public static void main(String[] args) {
        String str="8*9-8+4/2\n";

        LabeledExprLexer labeledExprLexer = new LabeledExprLexer(new ANTLRInputStream(str));

        CommonTokenStream commonTokenStream = new CommonTokenStream(labeledExprLexer);

        LabeledExprParser labeledExprParser = new LabeledExprParser(commonTokenStream);


        LabeledExprParser.ProgContext tree = labeledExprParser.prog();


        EvalVisitor evalVisitor = new EvalVisitor();

        Integer visit = evalVisitor.visit(tree);

        System.out.println(visit);
    }
}
