package cn.lianrf.classloader;

/**
 * Created by lianrongfa on 2018/1/10.
 */
public class ClassLoaderDemo {
    public static void main(String[] args) {
        ClassLoader classLoader = ClassLoaderDemo.class.getClassLoader();
        ClassLoader parent = classLoader.getParent();
        ClassLoader parent1 = parent.getParent();
    }
}
