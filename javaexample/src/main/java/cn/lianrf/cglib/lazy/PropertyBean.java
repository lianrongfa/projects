package cn.lianrf.cglib.lazy;

/**
 * Created by lianrongfa on 2018/2/24.
 */
public class PropertyBean {
    private String key;
    private Object value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "PropertyBean{" +
                "key='" + key + '\'' +
                ", value=" + value +
                '}';
    }
}
