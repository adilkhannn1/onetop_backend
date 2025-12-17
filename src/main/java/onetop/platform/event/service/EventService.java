package onetop.platform.event.service;

import onetop.platform.event.dto.AddUserToEventDTO;
import onetop.platform.event.mapper.EventMapper;
import onetop.platform.event.dto.EventDTO;
import onetop.platform.event.repository.EventRepository;
import onetop.platform.user.dto.UserDTO;
import onetop.platform.user.mapper.UserMapper;
import onetop.platform.user.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EventService {
    private final  EventRepository eventRepository;
    private final EventMapper eventMapper;
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public EventService(EventRepository eventRepository, EventMapper eventMapper, UserMapper userMapper, UserRepository userRepository){
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    public EventDTO creatEvent(Long userId, EventDTO eventDTO){
        var userEntity = userRepository.findById(userId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "You have to create an event by User"));
        if(!eventDTO.startDate().isBefore(eventDTO.endDate())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "StartDate should be before the endDate");
        }
         var eventEntity = eventMapper.toEntity(eventDTO);
         eventEntity.setCreator(userEntity);
         eventRepository.save(eventEntity);
         userEntity.getEvents().add(eventEntity);
         userRepository.save(userEntity);
         return eventMapper.toEventDTO(eventEntity);
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

    public EventDTO updateEvent(Long id, EventDTO eventDTO){
        if(!eventDTO.startDate().isBefore(eventDTO.endDate())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "StartDate should be before the endDate");
        }
        var eventEntity = eventRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found!"));
        eventEntity.setName(eventDTO.name());
        eventEntity.setDescription(eventDTO.description());
        eventEntity.setEventStatus(eventDTO.eventStatus());
        eventEntity.setEventType(eventDTO.eventType());
        eventEntity.setMaxPerson(eventDTO.maxPerson());
        eventEntity.setStartDate(eventDTO.startDate());
        return eventMapper.toEventDTO(eventEntity);
    }

    public String addUser(AddUserToEventDTO addUserToEventDTO){
        var eventEntity = eventRepository.findById(addUserToEventDTO.eventId()).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found"));
        var userEntity = userRepository.findById(addUserToEventDTO.userId()).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found"));
        if(eventEntity.getUsers().contains(userEntity)){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exists");
        }

        eventEntity.getUsers().add(userEntity);
        eventRepository.save(eventEntity);
        return "User added to the event";
    }

    public List<UserDTO> getAllUsersByEventId(Long id){
        var eventEntity = eventRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found"));
        var users = eventEntity.getUsers().stream().map(user -> userMapper.toUserDTO(user)).toList();
        return users;
    }

}
