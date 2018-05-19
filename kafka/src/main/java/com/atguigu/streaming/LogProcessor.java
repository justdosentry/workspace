package com.atguigu.streaming;

import org.apache.kafka.streams.processor.Processor;
import org.apache.kafka.streams.processor.ProcessorContext;

public class LogProcessor implements Processor<byte[], byte[]> {
    private ProcessorContext context;

    @Override
    public void init(ProcessorContext context) {
        this.context = context;
    }

    @Override
    public void process(byte[] bytes, byte[] bytes2) {
        String s = new String(bytes2);
        if (s.contains("<<<")) {
            String v1 = s.split("<<<")[1];
            context.forward(bytes, v1.getBytes());
        } else {
            context.forward(bytes, bytes2);
        }

    }

    @Override
    public void punctuate(long l) {}

    @Override
    public void close() {}
}