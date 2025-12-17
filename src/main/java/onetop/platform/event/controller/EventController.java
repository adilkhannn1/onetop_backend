package onetop.platform.event.controller;

import onetop.platform.event.dto.AddUserToEventDTO;
import onetop.platform.event.service.EventService;
import onetop.platform.event.dto.EventDTO;
import onetop.platform.user.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService){
        this.eventService = eventService;
    }

        @PostMapping("/users/{userId}")
        public ResponseEntity<EventDTO> creatEvent(@PathVariable Long userId,@RequestBody EventDTO eventDTO){
            var event = eventService.creatEvent(userId, eventDTO);
            return ResponseEntity.ok(event);
        }

    @GetMapping
    public ResponseEntity<List<EventDTO>> getAllEvents(){
         var events = eventService.getAllEvents();
         return ResponseEntity.ok(events);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDTO> getEventById(@PathVariable Long id){
        var event = eventService.getEventById(id);
        return ResponseEntity.ok(event);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEventById(@PathVariable Long id){
        eventService.deleteEventById(id);
        return ResponseEntity.ok("Event was deleted!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventDTO> updateEventById(@PathVariable Long id, @RequestBody EventDTO eventDTO){
        var updatedEvent = eventService.updateEvent(id, eventDTO);
        return ResponseEntity.ok(updatedEvent);
    }

    @PostMapping("/add_user")
    public ResponseEntity<String> addUserToEvent(@RequestBody AddUserToEventDTO addUserToEventDTO){
            var userEventResponse = eventService.addUser(addUserToEventDTO);
            return ResponseEntity.ok(userEventResponse);
    }

    @GetMapping("/get_users/{id}")
    public ResponseEntity<List<UserDTO>> getUsersByEventId(@PathVariable Long id){
        var users = eventService.getAllUsersByEventId(id);
        return ResponseEntity.ok(users);
    }

}

