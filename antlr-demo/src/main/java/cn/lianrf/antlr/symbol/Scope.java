package cn.lianrf.antlr.symbol;

/**
 * TODO
 *
 * @author lianrf
 * @version 1.0
 * @since 2021/12/3 9:49 上午
 */
public interface Scope {
    String getScopeName();

    /**
     * 获取下个作用域的符号表
     * Where to look next for symbols
     */
    Scope getEnclosingScope();

    /**
     * 在当前作用域中定义一个符号
     * Define a symbol in the current scope
     */
    void define(Symbol sym);

    /**
     * 如果不在此范围内，则在此范围内或封闭范围内查找名称
     * Look up name in this scope or in enclosing scope if not here
     */
    Symbol resolve(String name);
}
