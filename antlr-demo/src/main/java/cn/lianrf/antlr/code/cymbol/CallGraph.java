package cn.lianrf.antlr.code.cymbol;
/***
 * Excerpted from "The Definitive ANTLR 4 Reference",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material,
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose.
 * Visit http://www.pragmaticprogrammer.com/titles/tpantlr2 for more book information.
 ***/

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.misc.MultiMap;
import org.antlr.v4.runtime.misc.OrderedHashSet;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.stringtemplate.v4.ST;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CallGraph {
    /**
     * A graph model of the output. Tracks call from one function to
     * another by mapping function to list of called functions. E.g.,
     * f -> [g, h]
     * Can dump DOT two ways (StringBuilder and ST). Sample output:
     * digraph G {
     * ... setup ...
     * f -> g;
     * g -> f;
     * g -> h;
     * h -> h;
     * }
     */
    static class Graph {
        // 这里使用的是org.antlr.v4.runtime.misc: OrderedHashSet, MultiMap
        Set<String> nodes = new OrderedHashSet<>();
        // 函数列表
        MultiMap<String, String> edges = new MultiMap<>();  // 调用者->被调用者

        public void edge(String source, String target) {
            edges.map(source, target);
        }

        public String toString() {
            return "edges: " + edges.toString() + ", functions: " + nodes;
        }

        public String toDOT() {
            StringBuilder buf = new StringBuilder();
            buf.append("digraph G {\n");
            buf.append("  ranksep=.25;\n");
            buf.append("  edge [arrowsize=.5]\n");
            buf.append("  node [shape=circle, fontname=\"ArialNarrow\",\n");
            buf.append("        fontsize=12, fixedsize=true, height=.45];\n");
            buf.append("  ");
            for (String node : nodes) { // 首先打印所有节点
                buf.append(node);
                buf.append("; ");
            }
            buf.append("\n");

            for (Map.Entry<String, List<String>> entry : edges.entrySet()) {
                String src = entry.getKey();
                List<String> value = entry.getValue();
                for (String trg : value) {
                    buf.append("  ");
                    buf.append(src);
                    buf.append(" -> ");
                    buf.append(trg);
                    buf.append(";\n");
                }
            }
            /*for (String src : edges.keySet()) {
                for (String trg : edges.get(src)) {
                    buf.append("  ");
                    buf.append(src);
                    buf.append(" -> ");
                    buf.append(trg);
                    buf.append(";\n");
                }
            }*/
            buf.append("}\n");
            return buf.toString();
        }

        /**
         * Fill StringTemplate:
         * digraph G {
         * rankdir=LR;
         * <edgePairs:{edge| <edge.a> -> <edge.b>;}; separator="\n">
         * <childless:{f | <f>;}; separator="\n">
         * }
         * <p>
         * Just as an example. Much cleaner than buf.append method
         */
        public ST toST() {
            ST st = new ST(
                    "digraph G {\n" +
                            "  ranksep=.25; \n" +
                            "  edge [arrowsize=.5]\n" +
                            "  node [shape=circle, fontname=\"ArialNarrow\",\n" +
                            "        fontsize=12, fixedsize=true, height=.45];\n" +
                            "  <funcs:{f | <f>; }>\n" +
                            "  <edgePairs:{edge| <edge.a> -> <edge.b>;}; separator=\"\\n\">\n" +
                            "}\n"
            );
            st.add("edgePairs", edges.getPairs());
            st.add("funcs", nodes);
            return st;
        }
    }

    static class FunctionListener extends CymbolBaseListener {
        Graph graph = new Graph();
        String currentFunctionName = null;

        @Override
        public void enterFunctionDecl(CymbolParser.FunctionDeclContext ctx) {
            currentFunctionName = ctx.ID().getText();
            graph.nodes.add(currentFunctionName);
        }

        @Override
        public void exitCall(CymbolParser.CallContext ctx) {
            String funcName = ctx.ID().getText();
            // 将当前函数映射到被调用函数上
            graph.edge(currentFunctionName, funcName);
        }
    }

    public static void main(String[] args) throws Exception {


        InputStream resourceAsStream = CallGraph.class.getResourceAsStream("/t.cymbol");
        CharStream input = CharStreams.fromStream(resourceAsStream);

//        CharStream input = CharStreams.fromFileName("./t.cymbol");

        CymbolLexer lexer = new CymbolLexer(input);



        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CymbolParser parser = new CymbolParser(tokens);
        parser.setBuildParseTree(true);
        ParseTree tree = parser.file();
        // show tree in text form
//        System.out.println(tree.toStringTree(parser));
        parser.removeErrorListener();
        ParseTreeWalker walker = new ParseTreeWalker();
        FunctionListener collector = new FunctionListener();
        walker.walk(collector, tree);
        System.out.println(collector.graph.toString());
        System.out.println(collector.graph.toDOT());

        // Here's another example that uses StringTemplate to generate output
//        System.out.println(collector.graph.toST().render());
    }
}
