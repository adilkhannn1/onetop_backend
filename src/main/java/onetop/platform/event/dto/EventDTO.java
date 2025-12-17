package onetop.platform.event.dto;
import java.time.LocalDateTime;




public record EventDTO(
        Long id,
        String name,
        String description,
        EventStatus eventStatus,
        Integer maxPerson,
        Integer currentPerson,
        LocalDateTime startDate,
        LocalDateTime endDate,
        EventType eventType,
        EventCity city,
        String address
) {

}
