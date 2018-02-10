package cn.lianrf.classloader;

/**
 * Created by lianrongfa on 2018/1/10.
 */
public class ClassLoaderDemo {
    public static void main(String[] args) {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        ClassLoader parent = contextClassLoader.getParent();
        ClassLoader parent1 = parent.getParent();
    }
}
