package cn.lianrf.antlr.symbol;


import java.util.LinkedHashMap;
import java.util.Map;

/**
 *      |
 */


public abstract class BaseScope implements Scope {
    Scope enclosingScope; // 如果全局（最外层）范围为 null ，即为parent
    Map<String, Symbol> symbols = new LinkedHashMap<>();

    protected BaseScope(Scope enclosingScope) {
        this.enclosingScope = enclosingScope;
    }

    public Symbol resolve(String name) {
        Symbol s = symbols.get(name);
        if (s != null) return s;
        // if not here, check any enclosing scope
        if (enclosingScope != null) return enclosingScope.resolve(name);
        return null; // not found
    }

    public void define(Symbol sym) {
        symbols.put(sym.name, sym);
        sym.scope = this; // track the scope in each symbol
    }

    public Scope getEnclosingScope() {
        return enclosingScope;
    }

    public String toString() {
        return getScopeName() + ":" + symbols.keySet().toString();
    }
}
