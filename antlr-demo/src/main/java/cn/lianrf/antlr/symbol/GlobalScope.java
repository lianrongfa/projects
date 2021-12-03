package cn.lianrf.antlr.symbol;

/**
 * TODO
 *
 * @author lianrf
 * @version 1.0
 * @since 2021/12/3 9:51 上午
 */
public class GlobalScope extends BaseScope {
    public GlobalScope(Scope enclosingScope) {
        super(enclosingScope);
    }

    public String getScopeName() {
        return "globals";
    }
}
