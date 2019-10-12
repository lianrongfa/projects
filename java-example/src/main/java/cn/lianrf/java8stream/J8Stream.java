package cn.lianrf.java8stream;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @version: v1.0
 * @date: 2019/9/27
 * @author: lianrf
 */
public class J8Stream {
    /**
     * lambda
     *
     * 使用:: 双冒号
     *
     *
     */

    public static void main(String[] args) {
        of();
    }

    private static void of() {
        J8Stream j8Stream = new J8Stream();
        List<String> list = Arrays.asList("123", "fweg", "wegwe");
        List<Object> collect = Stream.of("123", "fweg", "wegwe").map(J8Stream::test).collect(Collectors.toList());

        System.out.println(collect);


    }

    public static String test(String i){
        return null;
    }
}
