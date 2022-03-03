package ar.edu.ues21.messagebrokerproducer.model;

import io.swagger.annotations.ApiModelProperty;

public class EventBase {
    private String eventId; //el id nunca lo estamos usando!
    @ApiModelProperty(name = "event type")
    private String eventType;
    @ApiModelProperty(name = "source")
    private String source;

    public EventBase() {
    }
    public String getEventType() {
        return eventType;
    }
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }
    public String getSource() {
        return source;
    }
    public void setSource(String source) {
        this.source = source;
    }

    public void setId(String id) {
        this.eventId = id;
    }
    public String getEventId() {
        return eventId;
    }
}
