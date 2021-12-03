package cn.lianrf.antlr.symbol;

/**
 * TODO
 *
 * @author lianrf
 * @version 1.0
 * @since 2021/12/3 9:53 上午
 */
public class LocalScope extends BaseScope {
    public LocalScope(Scope parent) {
        super(parent);
    }

    public String getScopeName() {
        return "locals";
    }
}
