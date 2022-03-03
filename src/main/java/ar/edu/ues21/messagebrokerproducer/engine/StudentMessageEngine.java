package ar.edu.ues21.messagebrokerproducer.engine;

import ar.edu.ues21.messagebrokerproducer.model.EventBase;
import ar.edu.ues21.messagebrokerproducer.model.StudentMessageEvent;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class StudentMessageEngine {
    @Value("${kafka.topic.student-message}")
    private String STUDENT_MESSAGE_TOPIC="student-message"; // este tampoco lo estamos usando, pero creo que es para en algun momento, tener mas de un topico

    private final Producer<String, StudentMessageEvent> studentMessageEventProducer;
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentMessageEngine.class);

    public StudentMessageEngine(Producer<String, StudentMessageEvent> studentMessageEventProducer) {
        this.studentMessageEventProducer = studentMessageEventProducer;
    }

    private <T extends EventBase> void sendMessage(@NotNull Producer<String, T> producer, String topic, T studentMessageEvent) {
        //  Boolean resendsActive = resendService.isTopicResendActive(topic);
        //aca el student-record-key de donde sale?... creo que debiera ir el id que no usamos nunca del studentMessageEvent
        producer.send(new ProducerRecord<>(topic, studentMessageEvent.getEventId(), studentMessageEvent), (metadata, exception) -> {
            //send(ProducerRecord<K, V> record, Callback callback);
            // callbackService.sendCallbackMessage(studentMessageEvent, metadata, exception);
            if (exception != null) {
                LOGGER.error("Encounter an error while sending event to kafka - EventId: {} - Error: {}",
                       studentMessageEvent.getEventId(), exception.getMessage());
                //        if (resendsActive) resendService.saveEventToResend(topic, studentMessageEvent);
            } else {
                LOGGER.info("Succesfully sended event of eventId {} to topic {} partition [{}] @ offset {}",
                        studentMessageEvent.getEventId(), metadata.topic(), metadata.partition(), metadata.offset());
            }
        });
    }

    public void sendStudentMessageEvent(StudentMessageEvent studentMessageEvent) {
        this.sendMessage(studentMessageEventProducer, STUDENT_MESSAGE_TOPIC, studentMessageEvent);
    }

}
