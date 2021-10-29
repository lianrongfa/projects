package cn.lianrf.antlr;

import cn.lianrf.antlr.out.expr.ExprBaseVisitor;
import cn.lianrf.antlr.out.expr.ExprLexer;
import cn.lianrf.antlr.out.expr.ExprParser;
import cn.lianrf.antlr.out.hello.HelloLexer;
import cn.lianrf.antlr.out.hello.HelloParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.UnbufferedCharStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;

/**
 * antlr test app
 *
 * @author lianrf
 * @version 1.0
 * @since 2021/10/28 11:00 上午
 */
public class AppTest {
    public static void main(String[] args) throws Exception {


        ANTLRInputStream inputStream = new ANTLRInputStream("1 + 2 + 3 * 4+ 6 / 2 \r\n");
        ExprLexer lexer = new ExprLexer(inputStream);

        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        ExprParser parser = new ExprParser(tokenStream);
        ParseTree parseTree = parser.prog();
        ExprBaseVisitor visitor = new ExprBaseVisitor();

        Object visit = visitor.visit(parseTree);

        System.out.println("#result#"+visit);
    }

    public static void run(){

        BigDecimal bigDecimal = new BigDecimal("1E-1");
        System.out.println(bigDecimal.toPlainString());

        String[] testStr={
                "Hello world",
                "hello world",
                "hi world"
        };
        for(String expr : testStr){
            // 对每一个输入的字符串，构造一个 ANTLRStringStream 流 in
            UnbufferedCharStream input = new UnbufferedCharStream(new ByteArrayInputStream(expr.getBytes()));
            // 用 in 构造词法分析器 lexer，词法分析的作用是将字符聚集成单词或者符号
            HelloLexer lexer = new HelloLexer(input);
            // 用词法分析器 lexer 构造一个记号流 tokens
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            // 再使用 tokens 构造语法分析器 parser,至此已经完成词法分析和语法分析的准备工作
            HelloParser parser = new HelloParser(tokens);
            // 最终调用语法分析器的规则 r（这个是我们在Hello.g4里面定义的那个规则），完成对表达式的验证
            HelloParser.RContext r = parser.r();

            System.out.println(r);
        }

    }

}
