package ar.edu.ues21.messagebrokerproducer.engine;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.ConsumerSeekAware;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class Consumer implements ConsumerSeekAware {
    private static final Logger LOGGER = LoggerFactory.getLogger(Consumer.class);

    @KafkaListener(topics = "${kafka.topic.student-message}", concurrency = "4",
            autoStartup = "true", containerFactory = "kafkaListenerContainerFactory")
    public void onStudentMessage(@Payload Map<String, Object> event, @Header(KafkaHeaders.OFFSET) Long offset,
                           @Header(KafkaHeaders.RECEIVED_PARTITION_ID) Integer partition,
                           @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        LOGGER.info("Processing topic = {}, partition = {}, offset = {} \n", topic, partition, offset);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            LOGGER.info(objectMapper.writeValueAsString(event));
        } catch (JsonProcessingException e) {
            LOGGER.info("Empty message");
            e.printStackTrace();
        }
    }
}

