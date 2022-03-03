package ar.edu.ues21.messagebrokerproducer.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)

//@ApiModel(description = "")
public class StudentMessageEvent extends EventBase{

    @ApiModelProperty(name = " student event data")
    private StudentMessage data;

    public StudentMessageEvent(StudentMessage data, String eventType, String source) {
        this.data = data;
        this.setEventType(eventType);
        this.setSource(source);

    }
}
