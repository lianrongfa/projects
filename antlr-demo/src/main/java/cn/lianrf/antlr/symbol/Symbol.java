package cn.lianrf.antlr.symbol;

/**
 * 符号基类
 *
 * @author lianrf
 * @version 1.0
 * @since 2021/12/3 9:50 上午
 */
public class Symbol {
    public enum Type {
        T_INVALID,//无效
        T_VOID,
        T_INT,
        T_FLOAT
    }

    String name;      // 所有符号至少有一个名字
    Type type;
    Scope scope;      // 所有符号都知道包含它们的范围。

    public Symbol(String name) {
        this.name = name;
    }

    public Symbol(String name, Type type) {
        this(name);
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        if (type != Type.T_INVALID) return '<' + getName() + ":" + type + '>';
        return getName();
    }
}