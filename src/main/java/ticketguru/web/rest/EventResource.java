package ticketguru.web.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ticketguru.domain.AppUser;
import ticketguru.domain.Event;
import ticketguru.repository.AppUserRepository;
import ticketguru.repository.EventRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/event")
public class EventResource {

    private final EventRepository eventRepository;

    private final AppUserRepository appUserRepository;

    public EventResource(EventRepository eventRepository, AppUserRepository appUserRepository) {
        this.eventRepository = eventRepository;
        this.appUserRepository = appUserRepository;
    }

    @PostMapping("")
    public ResponseEntity<Event> createEvent(@RequestBody Event event, @RequestParam Long userId) {
        if (event.getEventId() != null) {
            return ResponseEntity.badRequest().body(event);
        }
        AppUser user = appUserRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        event.setAppUser(user);
        event = eventRepository.save(event);
        return ResponseEntity.ok(event);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody Event event) {
        Optional<Event> existingEventOptional = eventRepository.findById(id);

        if (existingEventOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Event existingEvent = existingEventOptional.get();

        existingEvent.setEventName(event.getEventName());
        existingEvent.setEventDate(event.getEventDate());
        existingEvent.setLocation(event.getLocation());
        existingEvent.setAvailableTickets(event.getAvailableTickets());

        if (event.getAppUser() != null) {
            existingEvent.setAppUser(event.getAppUser());
        } else {
            existingEvent.setAppUser(existingEvent.getAppUser());
        }

        Event updatedEvent = eventRepository.save(existingEvent);
        return ResponseEntity.ok(updatedEvent);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEvent(@PathVariable Long id) {
        Optional<Event> event = eventRepository.findById(id);
        return ResponseEntity.of(event);
    }

    @GetMapping("")
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        if (!eventRepository.existsById(id)) {
            return ResponseEntity.notFound().build(); // Return 404 if event not found
        }
        eventRepository.deleteById(id); // Perform the delete operation
        return ResponseEntity.noContent().build(); // Return 204 No Content
    }
}
