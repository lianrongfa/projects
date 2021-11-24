package cn.lianrf.antlr.out.java;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;

/**
 * TODO
 *
 * @author lianrf
 * @version 1.0
 * @since 2021/11/17 7:33 下午
 */
public class JavaTest {
    public static void main(String[] args) throws IOException {


        JavaLexer javaLexer = new JavaLexer(new ANTLRInputStream("/***\n" +
                " * Excerpted from \"The Definitive ANTLR 4 Reference\",\n" +
                " * published by The Pragmatic Bookshelf.\n" +
                " * Copyrights apply to this code. It may not be used to create training material, \n" +
                " * courses, books, articles, and the like. Contact us if you are in doubt.\n" +
                " * We make no guarantees that this code is fit for any purpose. \n" +
                " * Visit http://www.pragmaticprogrammer.com/titles/tpantlr2 for more book information.\n" +
                "***/\n" +
                "import java.util.List;\n" +
                "import java.util.Map;\n" +
                "public class Demo {\n" +
                "\tvoid f(int x, String y) { }\n" +
                "\tint[ ] g(/*no args*/) { return null; }\n" +
                "\tList<Map<String, Integer>>[] h() { return null; }\n" +
                "}\n"));

        CommonTokenStream tokens = new CommonTokenStream(javaLexer);

        JavaParser parser = new JavaParser(tokens);

        ParseTreeWalker walker = new ParseTreeWalker();

        JavaParser.CompilationUnitContext tree = parser.compilationUnit();

        ExtractInterfaceListener listener = new ExtractInterfaceListener(parser);
        walker.walk(listener, tree);

    }
}

class ExtractInterfaceListener extends JavaBaseListener {
    JavaParser parser;

    public ExtractInterfaceListener(JavaParser parser) {
        this.parser = parser;
    }

    /**
     * Listen to matches of classDeclaration
     */
    @Override
    public void enterClassDeclaration(JavaParser.ClassDeclarationContext ctx) {
        System.out.println("interface I" + ctx.Identifier() + " {");
    }

    @Override
    public void exitClassDeclaration(JavaParser.ClassDeclarationContext ctx) {
        System.out.println("}");
    }

    /**
     * Listen to matches of methodDeclaration
     */
    @Override
    public void enterMethodDeclaration(JavaParser.MethodDeclarationContext ctx) {
        // need parser to get tokens
        TokenStream tokens = parser.getTokenStream();
        String type = "void";
        if (ctx.type() != null) {
            type = tokens.getText(ctx.type());
        }
        String args = tokens.getText(ctx.formalParameters());
        System.out.println("\t" + type + " " + ctx.Identifier() + args + ";");
    }

    @Override
    public void enterImportDeclaration(JavaParser.ImportDeclarationContext ctx) {
        System.out.println(parser.getTokenStream().getText(ctx));
    }
}