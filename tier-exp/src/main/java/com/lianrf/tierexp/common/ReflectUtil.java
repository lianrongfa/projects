package com.lianrf.tierexp.common;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * 反射工具类
 *
 * @author lianrf
 * @version 1.0
 * @since 2022/4/21 2:54 下午
 */
public class ReflectUtil {

    /**
     * 构造对象缓存
     */
    private static final SimpleCache<Class<?>, Constructor<?>[]> CONSTRUCTORS_CACHE = new SimpleCache<>();
    /**
     * 字段缓存
     */
    private static final SimpleCache<Class<?>, Field[]> FIELDS_CACHE = new SimpleCache<>();
    /**
     * 方法缓存
     */
    private static final SimpleCache<Class<?>, Method[]> METHODS_CACHE = new SimpleCache<>();


    /**
     * 查找方法，包含get方法和is方法，只返回匹配到的第一个方法，如果找不到对应的方法则返回{@code null}
     * 范围:public 类本身及父类
     *
     * @param clazz clazz
     * @param name  name
     * @return method
     */
    public static Method findMethodByGet(Class<?> clazz, String name) {
        if (name == null || "".equals(name)) {
            return null;
        }
        String firstUpper = String.valueOf(name.charAt(0)).toUpperCase() + name.substring(1);

        Method finalMethod = null;
        String[] methodNames = new String[]{"get" + firstUpper, "is" + firstUpper, name};
        for (String methodName : methodNames) {
            Method[] publicMethods = getPublicMethods(clazz);
            for (Method publicMethod : publicMethods) {
                if (StrUtil.equals(methodName, publicMethod.getName())) {
                    finalMethod = publicMethod;
                    break;
                }
            }
        }
        return finalMethod;
    }


    /**
     * 按照方法名查找指定方法名的方法，只返回匹配到的第一个方法，如果找不到对应的方法则返回{@code null}
     *
     * <p>
     * 此方法只检查方法名是否一致，并不检查参数的一致性。
     * </p>
     *
     * @param clazz      类，如果为{@code null}返回{@code null}
     * @param ignoreCase 是否忽略大小写
     * @param methodName 方法名，如果为空字符串返回{@code null}
     * @return 方法
     * @throws SecurityException 无权访问抛出异常
     * @since 4.3.2
     */
    public static Method getMethodByName(Class<?> clazz, boolean ignoreCase, String methodName) throws SecurityException {
        if (null == clazz || StrUtil.isBlank(methodName)) {
            return null;
        }

        final Method[] methods = getMethods(clazz);
        if (ArrayUtil.isNotEmpty(methods)) {
            for (Method method : methods) {
                if (StrUtil.equals(methodName, method.getName(), ignoreCase)) {
                    return method;
                }
            }
        }
        return null;
    }

    /**
     * 获得一个类中所有方法列表，包括其父类中的方法
     *
     * @param beanClass 类
     * @return 方法列表
     * @throws SecurityException 安全检查异常
     */
    public static Method[] getMethods(Class<?> beanClass) throws SecurityException {
        Method[] allMethods = METHODS_CACHE.get(beanClass);
        if (null != allMethods) {
            return allMethods;
        }

        allMethods = getMethodsDirectly(beanClass, true);
        return METHODS_CACHE.put(beanClass, allMethods);
    }

    /**
     * 获得一个类中所有方法列表，直接反射获取，无缓存
     *
     * @param beanClass             类
     * @param withSuperClassMethods 是否包括父类的方法列表
     * @return 方法列表
     * @throws SecurityException 安全检查异常
     */
    public static Method[] getMethodsDirectly(Class<?> beanClass, boolean withSuperClassMethods) throws SecurityException {

        Method[] allMethods = null;
        Class<?> searchType = beanClass;
        Method[] declaredMethods;
        while (searchType != null) {
            declaredMethods = searchType.getDeclaredMethods();
            if (null == allMethods) {
                allMethods = declaredMethods;
            } else {
                allMethods = ArrayUtil.append(allMethods, declaredMethods);
            }
            searchType = withSuperClassMethods ? searchType.getSuperclass() : null;
        }

        return allMethods;
    }


    /**
     * 获得指定类过滤后的Public方法列表
     *
     * @param clazz  查找方法的类
     * @param filter 过滤器
     * @return 过滤后的方法列表
     */
    public static List<Method> getPublicMethods(Class<?> clazz, Predicate<Method> filter) {
        if (null == clazz) {
            return null;
        }

        final Method[] methods = getPublicMethods(clazz);
        List<Method> methodList;
        if (null != filter) {
            methodList = new ArrayList<>();
            for (Method method : methods) {
                if (filter.test(method)) {
                    methodList.add(method);
                }
            }
        } else {
            methodList = Arrays.asList(methods);
        }
        return methodList;
    }


    /**
     * 获得本类及其父类所有Public方法
     *
     * @param clazz 查找方法的类
     * @return 过滤后的方法列表
     */
    public static Method[] getPublicMethods(Class<?> clazz) {
        return null == clazz ? null : clazz.getMethods();
    }


    /**
     * 获取对象的class，如果已经是class则直接返回
     *
     * @param obj obj
     * @return class
     */
    public static Class<?> getClass(Object obj) {
        return obj instanceof Class<?> ? (Class<?>) obj : obj.getClass();
    }
}
