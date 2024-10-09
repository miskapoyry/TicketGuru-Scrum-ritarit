package ticketguru.web.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ticketguru.domain.AppUser;
import ticketguru.domain.Event;
import ticketguru.DTO.EventDTO;
import ticketguru.repository.AppUserRepository;
import ticketguru.repository.EventRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/event")
public class EventResource {

    private final EventRepository eventRepository;
    private final AppUserRepository appUserRepository;

    public EventResource(EventRepository eventRepository, AppUserRepository appUserRepository) {
        this.eventRepository = eventRepository;
        this.appUserRepository = appUserRepository;
    }

    private EventDTO mapToDTO(Event event) {
        EventDTO eventDTO = new EventDTO();
        eventDTO.setEventId(event.getEventId());
        eventDTO.setEventName(event.getEventName());
        eventDTO.setEventDate(event.getEventDate());
        eventDTO.setLocation(event.getLocation());
        eventDTO.setTotalTickets(event.getTotalTickets());
        eventDTO.setAvailableTickets(event.getAvailableTickets());

        if (event.getAppUser() != null) {
            eventDTO.setAppUserId(event.getAppUser().getUserId());
        }

        return eventDTO;
    }

    private Event mapToEntity(EventDTO eventDTO, AppUser user) {
        Event event = new Event();
        event.setEventName(eventDTO.getEventName());
        event.setEventDate(eventDTO.getEventDate());
        event.setLocation(eventDTO.getLocation());
        event.setTotalTickets(eventDTO.getTotalTickets());
        event.setAvailableTickets(eventDTO.getAvailableTickets());
        event.setAppUser(user);
        return event;
    }

    @PostMapping("")
    public ResponseEntity<EventDTO> createEvent(@RequestBody EventDTO eventDTO) {
        if (eventDTO.getEventId() != null) {
            return ResponseEntity.badRequest().build();
        }
        AppUser user = appUserRepository.findById(eventDTO.getAppUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Event event = mapToEntity(eventDTO, user);
        event = eventRepository.save(event);

        EventDTO createdEventDTO = mapToDTO(event);
        return ResponseEntity.ok(createdEventDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventDTO> updateEvent(@PathVariable Long id, @RequestBody EventDTO eventDTO) {
        Optional<Event> existingEventOptional = eventRepository.findById(id);

        if (existingEventOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Event existingEvent = existingEventOptional.get();
        existingEvent.setEventName(eventDTO.getEventName());
        existingEvent.setEventDate(eventDTO.getEventDate());
        existingEvent.setLocation(eventDTO.getLocation());
        existingEvent.setAvailableTickets(eventDTO.getAvailableTickets());
        existingEvent.setTotalTickets(eventDTO.getTotalTickets());

        Event updatedEvent = eventRepository.save(existingEvent);
        EventDTO updatedEventDTO = mapToDTO(updatedEvent);
        return ResponseEntity.ok(updatedEventDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDTO> getEvent(@PathVariable Long id) {
        Optional<Event> event = eventRepository.findById(id);
        if (event.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        EventDTO eventDTO = mapToDTO(event.get());
        return ResponseEntity.ok(eventDTO);
    }

    @GetMapping("")
    public List<EventDTO> getAllEvents() {
        return eventRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        if (!eventRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        eventRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}