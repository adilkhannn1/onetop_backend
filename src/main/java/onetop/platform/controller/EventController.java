package onetop.platform.controller;

import onetop.platform.service.EventService;
import onetop.platform.model.dto.EventDTO;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("event")
public class EventController {

    public  final EventService eventService;

    public EventController(EventService eventService){
        this.eventService = eventService;
    }

    @PostMapping("create")
    public ResponseEntity<EventDTO> creatEvent(@RequestBody EventDTO eventDTO){
        var event = eventService.creatEvent(eventDTO);
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

}

