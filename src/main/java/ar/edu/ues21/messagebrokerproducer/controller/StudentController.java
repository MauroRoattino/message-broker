package ar.edu.ues21.messagebrokerproducer.controller;

import ar.edu.ues21.messagebrokerproducer.model.StudentMessage;
import ar.edu.ues21.messagebrokerproducer.model.StudentMessageEvent;
import ar.edu.ues21.messagebrokerproducer.service.ProducerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = "student.message") //
@RestController
@RequestMapping("/v1")
public class StudentController {
    private final ProducerService producerService;
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

    public StudentController(ProducerService producerService) {
        this.producerService = producerService;
    }

    @PostMapping(value = "/sendStudentMessageEvent",consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}) //
    public ResponseEntity<StudentMessage> sendStudentMessage(
            @RequestBody StudentMessage studentMessage,
            @RequestHeader(value = "eventType", defaultValue = "student-message") String eventType,
            @RequestHeader(value = "source", defaultValue = "-") String source) {

        LOGGER.info("Sending message");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            LOGGER.info(objectMapper.writeValueAsString(studentMessage));

        } catch (JsonProcessingException e) {
            LOGGER.info("Empty message");
            e.printStackTrace();
        }

        //------------ PRUEBA CON MENSAJE PROPIO------------------------------------------------
        StudentMessage mensaje =new StudentMessage("igna","monasterio",
                "leg12345","este es un mensaje lindo");

        try {
            LOGGER.info(objectMapper.writeValueAsString(mensaje));
        } catch (JsonProcessingException e) {
            LOGGER.info(" mensaje vacio");
            e.printStackTrace();
        }
        //------------------------------------------------------------
        producerService.sendStudentMessageEvent(studentMessage, eventType, source);
       // StudentMessageEvent response = new StudentMessageEvent(studentMessage, eventType, source);
        return ResponseEntity.ok(studentMessage);
    }

}
