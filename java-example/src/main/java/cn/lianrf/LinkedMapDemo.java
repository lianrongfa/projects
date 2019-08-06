package cn.lianrf;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;

/**
 * Created by lianrongfa on 2018/6/29.
 */
public class LinkedMapDemo {
    public static void main(String[] args) {
        LinkedHashMap<String, String> map = new LinkedHashMap<>(10,0.7f,true);
        for (int i=1;i<=11;i++){
            map.put(String.valueOf(i),String.valueOf(i));
        }

        map.get("2");

        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getValue());
        }
    }
}
