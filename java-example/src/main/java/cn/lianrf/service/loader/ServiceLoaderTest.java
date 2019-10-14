package cn.lianrf.service.loader;

import java.lang.reflect.Type;
import java.util.ServiceLoader;

/**
 * 用于加载 META-INF/services/ 目录下的配置文件类
 * 配置文件中的类需要实现load中的接口
 * @version: v1.0
 * @date: 2019/10/12
 * @author: lianrf
 */
public class ServiceLoaderTest {
    public static void main(String[] args) {
        ServiceLoader<LoaderInterface> load = ServiceLoader.load(LoaderInterface.class);
        /*for (LoaderInterface loaderInterface : load) {
           loaderInterface.test("123");
        }*/

        LoaderImpl3 impl3 = new LoaderImpl3();
        impl3.test("");
    }
}
