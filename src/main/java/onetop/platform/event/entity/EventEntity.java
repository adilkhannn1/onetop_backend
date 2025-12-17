package onetop.platform.event.entity;

import jakarta.persistence.*;
import onetop.platform.event.dto.EventCity;
import onetop.platform.event.dto.EventStatus;
import onetop.platform.event.dto.EventType;
import onetop.platform.user.entity.UserEntity;

import java.time.LocalDateTime;
import java.util.List;

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
    private LocalDateTime endDate;
    @Enumerated(EnumType.STRING)
    private EventType eventType;
    @Enumerated(EnumType.STRING)
    private EventCity city;
    private String address;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private UserEntity creator;


    @ManyToMany
    @JoinTable(name = "event_users")
    private List<UserEntity> users;

    public EventEntity(){

    }

    public EventEntity(String name, String description, EventStatus eventStatus, Integer maxPerson, Integer currentPerson, LocalDateTime startDate, LocalDateTime endDate, EventType eventType, EventCity city, String address){
        this.name = name;
        this.description = description;
        this.eventStatus = eventStatus;
        this.maxPerson = maxPerson;
        this.currentPerson = currentPerson;
        this.startDate = startDate;
        this.endDate = endDate;
        this.eventType = eventType;
        this.city = city;
        this.address = address;
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

    public LocalDateTime getEndDate() {
        return endDate;
    }


    public EventCity getCity() {
        return city;
    }



    public String getStreet() {
        return address;
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

    public List<UserEntity> getUsers() {
        return users;
    }

    public UserEntity getCreator(){
        return creator;
    }


    public void setCity(EventCity city) {
        this.city = city;
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

    public void setStreet(String address) {
        this.address = address;
    }

    public void setCurrentPerson(Integer currentPerson) {
        this.currentPerson = currentPerson;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public void setCreator(UserEntity creator){
        this.creator = creator;
    }

}
