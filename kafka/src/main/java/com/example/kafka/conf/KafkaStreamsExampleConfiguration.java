package com.example.kafka.conf;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.support.serializer.JsonSerde;

// 如果你的 @Bean 方法之间没有调用关系的话可以把 proxyBeanMethods 设置为 false 提高性能
@Configuration(proxyBeanMethods = false)
@EnableKafkaStreams
public class KafkaStreamsExampleConfiguration {
    @Bean
    public KStream<Integer,String> kStream(StreamsBuilder streamsBuilder){
        KStream<Integer,String> stream = streamsBuilder.stream("ks1In");
        stream.map((k,v) -> new KeyValue<>(k,v.toUpperCase())).to("ks1Out",
                Produced.with(Serdes.Integer(),new JsonSerde<>()));
        return stream;
    }
}
