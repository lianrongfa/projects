package cn.lianrf.java8stream;

import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @version: v1.0
 * @date: 2021/2/8
 * @author: lianrf
 */
public class StreamReduce {

    /**
     * reduce
     *
     * @param args
     */

    public static void main(String[] args) {
//        method1();
//        method2();
        method3();


    }

    /**
     * 方式一 ：一个入参 BinaryOperator<T>
     * x为上一次函数调用返回值，y为本次调用stream中的值
     * 注：第一次调用x为stream中的第一个值，y为stream中的第二个值;且stream中的元素个数大于等于2，reduce才有效
     */
    public static void method1() {
        Optional<Integer> reduce = Stream.of(1, 2).reduce((x, y) -> {
            System.out.println(x + ":" + y);
            return x + y;
        });
        System.out.println(reduce.get());
    }

    /**
     * 方式二：入参 identity和BinaryOperator<T>
     * x为上一次函数调用返回值，y为本次调用stream中的值
     * 注: 第一次调用x为identity的值，y为stream中的第一个值
     */
    public static void method2() {
        String reduce = Stream.of("f", "m").reduce("start", (x, y) -> {
            System.out.println(x + ":" + y);
            return y;
        });

        System.out.println(reduce);
    }

    /**
     * 方式三: 入参
     * U identity, 经过reduce后stream的返回值类型
     * BiFunction<U, ? super T, U> accumulator, 两个入参 第一个：identity定义的对象 第二个:steam中的元素 返回值第一个入参
     * BinaryOperator<U> combiner 普通stream中无用 具体使用详见{@link ParallelStream}
     */
    public static void method3() {

        //eg1
        HashSet<Character> reduce1 = Stream.of("a", "b", "c")
                .reduce(new HashSet<Character>(), (set, item) -> {
                    System.out.println("item:" + item);
                    set.add(item.toCharArray()[0]);
                    System.out.println(set);
                    return set;
                }, (x, y) -> {
                    System.out.println("param3:" + x + ":" + y);
                    return x;
                });
        System.out.println(reduce1);

        //eg2
        StringBuilder builder = new StringBuilder();
        StringBuilder reduce2 = Stream.of(1, 2, 3)
                .reduce(builder, StringBuilder::append, (x, y) -> {
                    return x;
                });
        System.out.println(reduce2);
    }
}
