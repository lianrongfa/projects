#[Javaagent使用](https://www.cnblogs.com/rickiyang/p/11368932.html)

##Javaagent 是什么？

Javaagent是java命令的一个参数。参数 javaagent 可以用于指定一个 jar 包，并且对该 java 包有2个要求：

1. 这个 jar 包的 MANIFEST.MF 文件必须指定 Premain-Class 项。
2. Premain-Class 指定的那个类必须实现 premain() 方法。

示例MANIFEST.MF文件:

> 12
> 
> 123 



api参考java.lang.instrument包

> * ClassDefinition
> * ClassFileTransformer
> * IllegalClassFormatException
> * Instrumentation
> * UnmodifiableClassException

该包提供了一些工具，可以让开发人员在运行时动态的修改class字节码

上面说到Premain-Class指定的类需要实现premain()方法，以下是该方法的格式
```
    public static void premain(String agentArgs, Instrumentation inst)
        
    public static void premain(String agentArgs)
```
JVM 会优先加载 带 Instrumentation 签名的方法，加载成功忽略第二种，如果第一种没有，则加载第二种方法。这个逻辑在sun.instrument.InstrumentationImpl 类中：

Instrumentation 类定义如下:
```java
public interface Instrumentation {
    //增加一个Class 文件的转换器，转换器用于改变 Class 二进制流的数据，参数 canRetransform 设置是否允许重新转换。
    void addTransformer(ClassFileTransformer transformer, boolean canRetransform);
    //在类加载之前，重新定义 Class 文件，ClassDefinition 表示对一个类新的定义，如果在类加载之后，需要使用 retransformClasses 方法重新定义。addTransformer方法配置之后，后续的类加载都会被Transformer拦截。对于已经加载过的类，可以执行retransformClasses来重新触发这个Transformer的拦截。类加载的字节码被修改后，除非再次被retransform，否则不会恢复。
    void addTransformer(ClassFileTransformer transformer);
    //删除一个类转换器
    boolean removeTransformer(ClassFileTransformer transformer);
    boolean isRetransformClassesSupported();
    //在类加载之后，重新定义 Class。这个很重要，该方法是1.6 之后加入的，事实上，该方法是 update 了一个类。
    void retransformClasses(Class<?>... classes) throws UnmodifiableClassException;
    boolean isRedefineClassesSupported();
    void redefineClasses(ClassDefinition... definitions)
        throws  ClassNotFoundException, UnmodifiableClassException;
    boolean isModifiableClass(Class<?> theClass);
    @SuppressWarnings("rawtypes")
    Class[] getAllLoadedClasses();
    @SuppressWarnings("rawtypes")
    Class[] getInitiatedClasses(ClassLoader loader);
    //获取一个对象的大小
    long getObjectSize(Object objectToSize);
    void appendToBootstrapClassLoaderSearch(JarFile jarfile);
    void appendToSystemClassLoaderSearch(JarFile jarfile);
    boolean isNativeMethodPrefixSupported();  
    void setNativeMethodPrefix(ClassFileTransformer transformer, String prefix);
}
```

[使用bytebuddy实现javaagent](https://www.jianshu.com/p/fe1448bf7d31)
maven依赖
````xml
<dependency>
    <groupId>net.bytebuddy</groupId>
    <artifactId>byte-buddy-agent</artifactId>
    <version>1.10.12</version>
</dependency>
````
构建插件
```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-shade-plugin</artifactId>
    <version>3.2.4</version>
    <executions>
        <execution>
            <phase>package</phase>
            <goals>
                <goal>shade</goal><!--重要，加上才会有Premain-Class-->
            </goals>
            <configuration>
                <artifactSet>
                    <includes>
                        <include>javassist:javassist:jar:</include>
                        <include>net.bytebuddy:byte-buddy:jar:</include>
                        <include>net.bytebuddy:byte-buddy-agent:jar:</include>
                    </includes>
                </artifactSet>
                <transformers>
                    <transformer
                            implementation="org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">
                        <manifestEntries>
                            <Main-Class>cn.lianrf.javaagent.AgentTest</Main-Class>
                            <Premain-Class>cn.lianrf.javaagent.MyAgent</Premain-Class>
                        </manifestEntries>
                    </transformer>
                </transformers>
            </configuration>
        </execution>
    </executions>
</plugin>
```
代码测试cn.lianrf.demo.agent.ApiTest，需要加上Javaagent参数，其中=testargs代表传入premain中的string参数
```
-javaagent:C:\Users\86180\Desktop\mymodel\code\projects\java-example\target\java-example-1.0.jar=testargs
```
