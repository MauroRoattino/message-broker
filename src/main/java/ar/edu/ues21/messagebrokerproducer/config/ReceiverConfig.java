package ar.edu.ues21.messagebrokerproducer.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
@EnableKafka
public class ReceiverConfig {

    @Value("${kafka.key}")
    private String KAFKA_KEY;

    @Value("${kafka.secret}")
    private String KAFKA_SECRET;

    private static final Logger LOGGER = LoggerFactory.getLogger(ReceiverConfig.class);

    @Bean
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put("bootstrap.servers", "pkc-4nym6.us-east-1.aws.confluent.cloud:9092");
        props.put("metadata.broker.list", "pkc-4nym6.us-east-1.aws.confluent.cloud:9092");

        props.put(ConsumerConfig.GROUP_ID_CONFIG, "student.message.cg");
        props.put("ssl.endpoint.identification.algorithm", "https");
        props.put("security.protocol", "SASL_SSL");
        props.put("sasl.mechanism", "PLAIN");
        props.put("sasl.jaas.config", "org.apache.kafka.common.security.plain.PlainLoginModule required username=\""
                + KAFKA_KEY +"\" password=\"" +KAFKA_SECRET+ "\";");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, HashMap.class);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        props.put(JsonDeserializer.USE_TYPE_INFO_HEADERS, false);
        props.put(JsonDeserializer.REMOVE_TYPE_INFO_HEADERS, true);
        return props;
    }


    public ConsumerFactory<String, Map<String, Object>> consumerFactory() {
        Map<String, Object> consumerConfigs = consumerConfigs();
        JsonDeserializer<Map<String, Object>> valueDeserializer = new JsonDeserializer<>();

        valueDeserializer.configure(consumerConfigs, false);

        StringDeserializer keyDeserializer = new StringDeserializer();
        keyDeserializer.configure(consumerConfigs, true);
        return new DefaultKafkaConsumerFactory<>(consumerConfigs, keyDeserializer,
                valueDeserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Map<String, Object>> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Map<String, Object>> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

}
