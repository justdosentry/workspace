package com.atguigu.produce;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Produce {

    public static void main(String[] args) throws InterruptedException {

        Properties props = new Properties();
        props.put("bootstrap.servers", "hadoop01:9092,hadoop02:9092,hadoop03:9092");
        // 等待所有副本节点的应答
        props.put("acks", "all");
        // 消息发送最大尝试次数
        props.put("retries", 0);
        // 一批消息处理大小
        props.put("batch.size", 16384);
        // 请求延时
        props.put("linger.ms", 1);
        // 发送缓存区内存大小
        props.put("buffer.memory", 33554432);
        // key序列化
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        // value序列化
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        //自定义分区
//        props.put("partitioner.class","com.atguigu.produce.CustomPartition");

        //设置拦截链
//        List<String> list = new ArrayList();
//        list.add("com.atguigu.intercept.AddIntercept");
//        list.add("com.atguigu.intercept.CountIntercept");
//        props.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG,list);
        //获取producer
        KafkaProducer producer = new KafkaProducer(props);

        for (int i = 0; i < 50; i++) {
            //不写key,消息是写入到一个分区中的
            producer.send(new ProducerRecord("sentry",0,"sentry_" + i));
            //写key是通过key的hash值写入到不同分区中的
//            producer.send(new ProducerRecord("sentry", String.valueOf(i), "sentry_" + i), (recordMetadata, e) -> {
//                if (recordMetadata != null) {
//                    System.out.println(recordMetadata.partition() + "-" + recordMetadata.topic() + "-" + recordMetadata.offset());
//                }
//            });
//            //直接指定分区
//            producer.send(new ProducerRecord("sentry", 0,String.valueOf(i), "sentry_" + i), (recordMetadata, e) -> {
//                if (recordMetadata != null) {
//                    System.out.println(recordMetadata.partition() + "-" + recordMetadata.topic() + "-" + recordMetadata.offset());
//                }
//            });
            //自定义分区
//            producer.send(new ProducerRecord("sentry",String.valueOf(i), "sentry_" + i), (recordMetadata, e) -> {
//                if (recordMetadata != null) {
//                    System.out.println(recordMetadata.partition() + "-" + recordMetadata.topic() + "-" + recordMetadata.offset());
//                }
//            });
            Thread.sleep(300);
        }
           producer.close();
    }

}
