package cn.lianrf.springbootjar;

import org.springframework.boot.loader.LaunchedURLClassLoader;
import org.springframework.boot.loader.jar.JarFile;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @version: v1.0
 * @date: 2021/4/22
 * @author: lianrf
 */
public class LaunchedURLClassLoaderDemo {

    public static void main(String[] args) throws ClassNotFoundException, MalformedURLException {

//        URL url = new URL("jar:file:/C:/Users/86180/Desktop/arthas-boot.jar!/");
//
//        String file = url.getFile();
//
//        System.out.println(file);

        extracted();
    }

    private static void extracted() throws MalformedURLException, ClassNotFoundException {
        JarFile.registerUrlProtocolHandler();
        // 构造LaunchedURLClassLoader类加载器，这里使用了2个URL，分别对应jar包中依赖包spring-boot-loader和spring-boot，使用 "!/" 分开，需要org.springframework.boot.loader.jar.Handler处理器处理
        LaunchedURLClassLoader classLoader = new LaunchedURLClassLoader(
                new URL[]{
                        new URL("jar:file:/C:/Users/86180/Desktop/arthas-boot.jar!/")
                        //, new URL("jar:file:/E:/IdeaProjects/oneday-auth/oneday-auth-server/target/oneday-auth-server-1.0.0-SNAPSHOT.jar!/BOOT-INF/lib/spring-boot-2.1.3.RELEASE.jar!/")
                        //,new URL("jar:file:/E:/IdeaProjects/oneday-auth/oneday-auth-server/target/oneday-auth-server-1.0.0-SNAPSHOT.jar!/BOOT-INF/lib/spring-boot-loader-1.2.3.RELEASE.jar!/")
                },
                LaunchedURLClassLoaderDemo.class.getClassLoader());
        // 加载类
        Class<?> aClass = classLoader.loadClass("com.taobao.middleware.cli.Option");

        System.out.println(aClass);
        // 这2个类都会在第二步本地查找中被找出(URLClassLoader的findClass方法)
        //classLoader.loadClass("org.springframework.boot.loader.JarLauncher");
        //classLoader.loadClass("org.springframework.boot.SpringApplication");
        // 在第三步使用默认的加载顺序在ApplicationClassLoader中被找出
        //classLoader.loadClass("org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration");

        //SpringApplication.run(Application.class, args);
    }
}
