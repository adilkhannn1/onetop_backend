package onetop.platform.model.entity;

import jakarta.persistence.*;
import onetop.platform.model.dto.EventStatus;
import onetop.platform.model.dto.EventType;

import java.time.LocalDateTime;

@Entity
@Table(name = "events")
public class EventEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    private EventStatus eventStatus;
    private Integer maxPerson;
    private Integer currentPerson;
    private LocalDateTime startDate;
    @Enumerated(EnumType.STRING)
    private EventType eventType;



    public EventEntity(){

    }

    public EventEntity(String name, String description, EventStatus eventStatus, Integer maxPerson, Integer currentPerson, LocalDateTime startDate, EventType eventType){
        this.name = name;
        this.description = description;
        this.eventStatus = eventStatus;
        this.maxPerson = maxPerson;
        this.currentPerson = currentPerson;
        this.startDate = startDate;
        this.eventType = eventType;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public EventStatus getEventStatus() {
        return eventStatus;
    }

    public Integer getMaxPerson() {
        return maxPerson;
    }

    public Integer getCurrentPerson() {
        return currentPerson;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEventStatus(EventStatus eventStatus) {
        this.eventStatus = eventStatus;
    }

    public void setMaxPerson(Integer maxPerson) {
        this.maxPerson = maxPerson;
    }

    public void setCurrentPerson(Integer currentPerson) {
        this.currentPerson = currentPerson;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

}
