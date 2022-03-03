package ar.edu.ues21.messagebrokerproducer.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@ApiModel(value = "StudentMessage", description = "This Enrollment is retrieved from the " +
        "[Admission API](https://api.ues21.edu.ar/admission-api/api/swagger-ui.html#/default-enrollment-controller/getContextUsingGET)")
@Data
@Builder
@NoArgsConstructor
//@AllArgsConstructor
public class StudentMessage {

        @ApiModelProperty(value = "the name of the student")
        private String name;

        @ApiModelProperty(value = "the last name of the student")
        private String lastName;

        @ApiModelProperty(value = "the student id")
        private String studentRecord;

        @ApiModelProperty(value = "the student message")
        private String message;
//agrego un constructor para probar con un mensaje creado por mi... no se si esto debiera aplicar para el mensaje que
        //llega desde swagger. Porque supuestamente con la anotacion allArgCostructor, ya esta creado.
        public StudentMessage(String name, String lastName, String studentRecord, String message) {
                this.name = name;
                this.lastName = lastName;
                this.studentRecord = studentRecord;
                this.message = message;

}
//agrego los geters y seters dela clase, porque sino no los imprime.
        public void setName(String name) {
                this.name = name;
        }

        public String getLastName() {
                return lastName;
        }

        public void setLastName(String lastName) {
                this.lastName = lastName;
        }

        public String getStudentRecord() {
                return studentRecord;
        }

        public void setStudentRecord(String studentRecord) {
                this.studentRecord = studentRecord;
        }

        public String getMessage() {
                return message;
        }

        public void setMessage(String message) {
                this.message = message;
        }

        public String getName() {
                return name;
        }
}
