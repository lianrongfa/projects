package cn.lianrf;

import com.alibaba.dubbo.container.Main;

/**
 * Created by lianrongfa on 2018/6/20.
 * Main启动需要在resource/META-INF 放入spring.xml
 *默认使用spring启动
 */
public class MainStart {
    public static void main(String[] args) {
        Main.main(new String[]{"spring","log4j"});
    }
}
