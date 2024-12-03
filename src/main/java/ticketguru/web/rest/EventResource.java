package ticketguru.web.rest;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ticketguru.exception.ResourceNotFoundException;
import ticketguru.service.EventService;
import ticketguru.DTO.EventDTO;
import ticketguru.DTO.EventReportDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/events")
public class EventResource {

    @Autowired
    private EventService eventService;

    @PostMapping
    public ResponseEntity<EventDTO> createEvent(@Valid @RequestBody EventDTO eventDTO) {
        EventDTO createdEvent = eventService.createEvent(eventDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEvent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventDTO> updateEvent(@PathVariable Long id, @Valid @RequestBody EventDTO eventDTO) {
        EventDTO updatedEvent = eventService.updateEvent(id, eventDTO);
        return ResponseEntity.ok(updatedEvent);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDTO> findEventById(@PathVariable Long id) {
        EventDTO event = eventService.findEventById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event with ID " + id + " not found"));
        return ResponseEntity.ok(event);
    }

    @GetMapping("/{id}/report")
    public ResponseEntity<List<EventReportDTO>> generateEventReport(@PathVariable Long id) {
        List<EventReportDTO> report = eventService.generateEventReport(id);
        return ResponseEntity.ok(report);
    }

    @GetMapping
    public List<EventDTO> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchEvents(@Valid @RequestParam(required = false) String eventName,
                                          @RequestParam(required = false) String location) {
        try {
            List<EventDTO> events = eventService.searchEvents(eventName, location);
            if (events.isEmpty()) {
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("message", "No events found with the given criteria");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            }
            return ResponseEntity.ok(events);
        } catch (IllegalArgumentException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        try {
            eventService.deleteEvent(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}