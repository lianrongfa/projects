package cn.lianrf.stream;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.KTable;

import java.util.Properties;
import java.util.concurrent.CountDownLatch;

/**
 * TODO
 *
 * @author lianrf
 * @version 1.0
 * @since 2022/3/28 4:13 下午
 */
public class StreamDemo {
    public static void main(String[] args) {
        System.out.println("1".length());
    }

    private static void extracted() {
        Properties prop =new Properties();
        prop.put(StreamsConfig.APPLICATION_ID_CONFIG,"mystream");
        prop.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG,"192.168.136.20:9092"); //zookeeper的地址
        prop.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass()); //输入key的类型
        prop.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG,Serdes.String().getClass());  //输入value的类型

        //创建流构造器
        StreamsBuilder builder = new StreamsBuilder();

        //构建好builder，将myStreamIn topic中的数据写入到myStreamOut topic中
        builder.stream("myStreamIn").to("myStreamOut");


        KTable<Object, Object> kTable = builder.stream("").toTable();

//        builder.stream("").groupBy().windowedBy()


        final Topology topo=builder.build();
        final KafkaStreams streams = new KafkaStreams(topo, prop);

        final CountDownLatch latch = new CountDownLatch(1);
        Runtime.getRuntime().addShutdownHook(new Thread("stream"){
            @Override
            public void run() {
                streams.close();
                latch.countDown();
            }
        });
        try {
            streams.start();
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
}
