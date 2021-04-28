package cn.lianrf.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @version: v1.0
 * @date: 2019/8/6
 * @author: lianrf
 */
public class ConsumerDemo {

    private static String groupId="dba4";
    private static String consumerId="consumer.test3";
    private static AtomicBoolean isRun=new AtomicBoolean(true);

    public static Properties initConfig(){
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringDeserializer");
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringDeserializer");
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG,groupId);
        properties.setProperty(ConsumerConfig.CLIENT_ID_CONFIG,consumerId);
        /**
         * earliest表示每个消费组从最早的消息开始消费，及第一个消息
         * latest表示从分区末尾开始消费消息
         * 默认为latest
         */
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");
        /*取消自动提交*/
        //properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,false);
        /**
         * {@link}
         */
        return properties;
    }

    public static void main(String[] args) {
        Properties properties = initConfig();
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);

        subscribe2(consumer);

        while (isRun.get()){
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
            for (ConsumerRecord<String, String> record : records) {
                System.out.println("key:"+record.key()+" value:"+record.value()+" offset:"+record.offset());
            }

        }

    }

    //--------------------------[订阅]---------------------------
    /**
     * 直接消费topic
     * @param consumer
     */
    private static void subscribe1(KafkaConsumer<String, String> consumer) {
        consumer.subscribe(Arrays.asList("test"));
    }

    /**
     * 消费指定分区消息
     * 如果不知道分区信息
     * 可以从 {@link KafkaConsumer#partitionsFor(String)} 获得分区消息信息
     * @param consumer
     */
    private static void subscribe2(KafkaConsumer<String, String> consumer) {

       consumer.assign(Arrays.asList(new TopicPartition("test",0)));
    }

    //--------------------------[取消订阅]---------------------------
    private static void unSubscribe(KafkaConsumer<String, String> consumer) {
        //方式1
        consumer.unsubscribe();
        //方式2
        consumer.subscribe(new ArrayList<String>());
        //方式3
        consumer.assign(new ArrayList<TopicPartition>());
    }

}
