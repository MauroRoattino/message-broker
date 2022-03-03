package ar.edu.ues21.messagebrokerproducer.service;

import ar.edu.ues21.messagebrokerproducer.engine.StudentMessageEngine;
import ar.edu.ues21.messagebrokerproducer.model.StudentMessage;
import ar.edu.ues21.messagebrokerproducer.model.StudentMessageEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
@Service
public class ProducerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProducerService.class);

    private final StudentMessageEngine studentMessageEngine;

    @Autowired
    public ProducerService(StudentMessageEngine studentMessageEngine) {
        this.studentMessageEngine = studentMessageEngine;
    }
    @Async
    public void sendStudentMessageEvent(StudentMessage studentMessage, String eventType, String source) {
        //this.processEnrollmentEvent(enrollmentResponse);
        StudentMessageEvent studentMessageEvent = new StudentMessageEvent(studentMessage, eventType, source);
        studentMessageEvent.setId("a123");
        studentMessageEngine.sendStudentMessageEvent(studentMessageEvent);

    }
}
