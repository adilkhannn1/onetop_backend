package onetop.platform.model.dto;
import java.time.LocalDateTime;




public record EventDTO(
        Long id,
        String name,
        String description,
        EventStatus eventStatus,
        Integer maxPerson,
        Integer currentPerson,
        LocalDateTime startDate,
        EventType eventType
) {

}
