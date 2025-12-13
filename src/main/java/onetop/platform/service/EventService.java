package onetop.platform.service;

import onetop.platform.mapper.EventMapper;
import onetop.platform.model.dto.EventDTO;
import onetop.platform.model.entity.EventEntity;
import onetop.platform.repository.eventRepository.EventRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EventService {
    private final  EventRepository eventRepository;
    private final EventMapper eventMapper;

    public EventService( EventRepository eventRepository, EventMapper eventMapper){
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
    }

    public EventDTO creatEvent(EventDTO eventDTO){
         var entity = eventMapper.toEntity(eventDTO);
         eventRepository.save(entity);
         return eventMapper.toEventDTO(entity);
    }

    public List<EventDTO> getAllEvents(){
        var eventEntityList = eventRepository.findAll();
        var toEventDTOList = eventMapper.toEventListDTO(eventEntityList);
        return toEventDTOList;
    }

    public EventDTO getEventById(Long id){
        var eventEntity = eventRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found!"));
        return eventMapper.toEventDTO(eventEntity);
    }
    public void deleteEventById(Long id){
        var eventEntity = eventRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found!"));
        eventRepository.deleteById(id);
    }

}
