package onetop.platform.mapper;

import onetop.platform.model.dto.EventDTO;
import onetop.platform.model.entity.EventEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EventMapper {
    public EventEntity toEntity(EventDTO eventDTO){
        return new EventEntity(eventDTO.name(), eventDTO.description(), eventDTO.eventStatus(), eventDTO.maxPerson(),
                eventDTO.currentPerson(), eventDTO.startDate(), eventDTO.eventType());
    }
    public EventDTO toEventDTO(EventEntity eventEntity){
        return new EventDTO(eventEntity.getId(), eventEntity.getName(), eventEntity.getDescription(), eventEntity.getEventStatus(),
                eventEntity.getMaxPerson(),
                eventEntity.getCurrentPerson(), eventEntity.getStartDate(), eventEntity.getEventType());
    }
    public List<EventDTO> toEventListDTO(List<EventEntity> eventEntityList){
        List<EventDTO> listOfEvenDTO = eventEntityList.stream().map(
                entity-> new EventDTO(
                        entity.getId(),
                        entity.getName(),
                        entity.getDescription(),
                        entity.getEventStatus(),
                        entity.getMaxPerson(),
                        entity.getCurrentPerson(),
                        entity.getStartDate(),
                        entity.getEventType())).toList();
        return listOfEvenDTO;
    }

}
