package com.atguigu.intercept;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

public class CountIntercept implements ProducerInterceptor<String, String> {
    private long errcount = 0;
    private long successcount = 0;


    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> producerRecord) {
        return producerRecord;
    }

    @Override
    public void onAcknowledgement(RecordMetadata recordMetadata, Exception e) {
        if (recordMetadata != null) {
            successcount++;
        } else {
            errcount++;
        }
    }

    @Override
    public void close() {
        System.out.println("成功条数: " + successcount);
        System.out.println("失败条数: " + errcount);
    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
