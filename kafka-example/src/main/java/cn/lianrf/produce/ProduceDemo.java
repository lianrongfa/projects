package cn.lianrf.produce;

import org.apache.kafka.clients.KafkaClient;
import org.apache.kafka.clients.producer.*;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * 发送消息主要有三种模式：发后即忘（ fire -and- forg 〕、同步（ sync ）及异步 async ）。
 * @version: v1.0
 * @date: 2019/8/6
 * @author: lianrf
 */
public class ProduceDemo {

    public static Properties initConfig(){
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        return properties;
    }

    public static void main(String[] args) {

        KafkaProducer<String,String> producer=new KafkaProducer<String, String>(initConfig());


        test2(producer);

    }

    /**
     * 异步回调
     * @param producer
     */
    private static void test2(KafkaProducer<String, String> producer) {
        for (int i = 0; i <100 ; i++) {
            producer.send(new ProducerRecord<String, String>("test", i+" call back test..."), (v, e)-> {
                if(e!=null){
                    e.printStackTrace();
                }else {
                    System.out.println(v);

                }
            });
        }

        producer.flush();
    }

    /**
     * future同步方式发送数据
     * @param producer
     */
    private static void test1(KafkaProducer<String, String> producer) {
        Future<RecordMetadata> future = producer.send(
                new ProducerRecord<String, String>("test", "456"));
        try {
            RecordMetadata recordMetadata = future.get();
            System.out.println(recordMetadata);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发后即忘（ fire -and- forg 〕
     * @param producer
     */
    private static void test0(KafkaProducer<String, String> producer) {
        Future<RecordMetadata> future = producer.send(
                new ProducerRecord<String, String>("test", "test0"));

        producer.flush();
    }
}
