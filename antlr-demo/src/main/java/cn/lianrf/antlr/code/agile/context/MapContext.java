package cn.lianrf.antlr.code.agile.context;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lianrf
 * @version 1.0
 * @since 2022/2/17 5:54 下午
 */
public class MapContext implements ExpContext {

    public MapContext() {
    }

    public MapContext(Map<String, Object> map) {
        if (map != null) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                this.set(entry.getKey(), entry.getValue());
            }
        }
    }

    private final Map<String, Var> map = new HashMap<>();

    @Override
    public Object get(String name) {
        Var v = map.get(name);
        if (v != null) {
            return v.getValue();
        }
        return null;
    }

    @Override
    public void set(String name, Object value) {
        // 如果变量已经存在，就不再重复创建变量。
        Var obj = getVar(name);
        if (obj != null) {
            obj.setValue(value);
        } else {
            this.map.put(name, new Var(name, value));
        }
    }

    @Override
    public Var getVar(String name) {
        return this.map.get(name);
    }

    @Override
    public void setVar(Var v) {
        if (v == null) {
            throw new NullPointerException();
        }
        this.map.put(v.getName(), v);
    }
}
