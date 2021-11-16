package cn.lianrf.antlr.out.array;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;

/**
 * TODO
 *
 * @author lianrf
 * @version 1.0
 * @since 2021/11/15 3:09 下午
 */
public class ArrayTest {
    public static void main(String[] args) throws IOException {
//输入流
        ANTLRInputStream stream = new ANTLRInputStream("{99,3,{1,2},451}");
//词法分析器
        ArrayInitLexer lexer = new ArrayInitLexer(stream);
//用于存储词法分析器将生成的词法符号
        CommonTokenStream tokens = new CommonTokenStream(lexer);
//处理词法符号缓冲区中的内容
        ArrayInitParser parser = new ArrayInitParser(tokens);
//针对init规则开始语法分析
        ParseTree tree = parser.init();
//语法分析树遍历器，能触发回调函数
        ParseTreeWalker treeWalker = new ParseTreeWalker();
        treeWalker.walk(new MyCustomListener(),tree);

        System.out.println();
    }
}
class MyCustomListener extends ArrayInitBaseListener{

    /*将{翻译为"*/
    @Override
    public void enterInit(ArrayInitParser.InitContext ctx) {
        System.out.print('"');
    }

    /*将}翻译为"*/
    @Override
    public void exitInit(ArrayInitParser.InitContext ctx) {
        System.out.print('"');
    }

    /***
     * 将每个整数翻译为十六进制，然后加上前缀\\u
     */
    @Override
    public void enterValue(ArrayInitParser.ValueContext ctx) {

        //从上下文对象中获取INT词法符号对应的整数值，该词法符号由匹配value规则得来。
        String text = null;
        try {
            text = ctx.INT().getText();
            Integer value = Integer.valueOf(text);
            System.out.printf("\\u%04x",value);
        } catch (Exception e) {
            //忽略掉嵌套结构
        }
    }
}