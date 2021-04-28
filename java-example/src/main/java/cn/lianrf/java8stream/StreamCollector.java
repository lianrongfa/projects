package cn.lianrf.java8stream;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @version: v1.0
 * @date: 2021/2/22
 * @author: lianrf
 */
public class StreamCollector {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 6, 7, 8, 9);

        method1(list);
        method3(list);

        StringJoiner stringJoiner = new StringJoiner(";");

        stringJoiner.add("123");
        stringJoiner.add("123");
        stringJoiner.add("123");
        stringJoiner.add("123");
        stringJoiner.add("123");

        System.out.println(stringJoiner.toString());
    }

    /**
     * 使用jdk封装好的Collectors
     * collect(Collector<? super T, A, R> collector)
     * @param list source
     */
    private static void method1(List<Integer> list) {
        //1.joining
        String collect = list.stream().map(String::valueOf).collect(Collectors.joining());
        System.out.println(collect);

        collect = list.stream().map(String::valueOf).collect(Collectors.joining(",","[","]"));
        System.out.println(collect);

    }

    /**
     * 自定义Collector
     * {@link java.util.stream.Collector}
     * @param list source
     */
    private static void method3(List<Integer> list) {
        //此处为方便查看采用匿名类
        Object collect = list.stream().collect(new Collector<Integer, StringBuilder, String>() {
            @Override
            public Supplier<StringBuilder> supplier() {
                return StringBuilder::new;
            }

            @Override
            public BiConsumer<StringBuilder, Integer> accumulator() {
                return new BiConsumer<StringBuilder, Integer>() {
                    @Override
                    public void accept(StringBuilder stringBuilder, Integer integer) {
                        stringBuilder.append(integer).append(",");
                    }
                };
            }

            @Override
            public BinaryOperator<StringBuilder> combiner() {
                return StringBuilder::append;
            }

            @Override
            public Function<StringBuilder, String> finisher() {
                return StringBuilder::toString;
            }

            @Override
            public Set<Characteristics> characteristics() {
                return Collections.emptySet();
            }
        });
        System.out.println(collect);
    }

    /**
     * collect(
     * Supplier<R> supplier  用来创建返回指定类型的数据对象
     * ,BiConsumer<R, ? super P_OUT> accumulator  用于将stream中的元素封装为supplier构建的数据类型
     * ,BiConsumer<R, R> combiner 并行stream时可用
     * )
     *
     * @param list source
     */
    private static void method2(List<Integer> list) {
//        ArrayList<Object> collect = list.parallelStream().collect(ArrayList::new, List::add, (x,y)->{
//            x.addAll(y);
//        });

        Entity collect = list.stream().collect(Entity::new, (x, y) -> {
            x.name = "";
            x.i = y;
        }, (x, y) -> {

        });
        System.out.println(collect);
    }

}
