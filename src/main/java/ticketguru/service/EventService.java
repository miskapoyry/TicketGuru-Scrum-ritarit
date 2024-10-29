package ticketguru.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ticketguru.DTO.EventDTO;
import ticketguru.domain.AppUser;
import ticketguru.domain.Event;
import ticketguru.repository.AppUserRepository;
import ticketguru.repository.EventRepository;
import ticketguru.domain.EventTicketType;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    public Event createEvent(Event event, Long userId) {
        if (event.getEventId() != null) {
            throw new IllegalArgumentException("New event cannot already have an ID");
        }
        AppUser user = appUserRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        event.setAppUser(user);
        return eventRepository.save(event);
    }

    public Event updateEvent(Long id, Event event) {
        Optional<Event> existingEventOptional = eventRepository.findById(id);

        if (existingEventOptional.isEmpty()) {
            throw new IllegalArgumentException("Event not found");
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

        return eventRepository.save(existingEvent);
    }

    public Optional<EventDTO> findEventById(Long id) {
        Optional<Event> optionalEvent = eventRepository.findById(id);
        return optionalEvent.map(this::convertToEventDTO);
    }

    public List<EventDTO> getAllEvents() {
        return eventRepository.findAll().stream()
                .map(this::convertToEventDTO)
                .collect(Collectors.toList());
    }

    public List<EventDTO> searchEvents(String eventName, String location) {
        if ((eventName == null || eventName.isEmpty()) && (location == null || location.isEmpty())) {
            throw new IllegalArgumentException("Either eventName or location must be provided");
        }

        List<Event> events;
        if (eventName != null && location != null) {
            events = eventRepository.findByEventNameContainsIgnoreCaseAndLocationContainsIgnoreCase(eventName, location);
        } else if (eventName != null) {
            events = eventRepository.findByEventNameContainsIgnoreCase(eventName);
        } else {
            events = eventRepository.findByLocationContainsIgnoreCase(location);
        }
        return events.stream()
                .map(this::convertToEventDTO)
                .collect(Collectors.toList());
    }

    public void deleteEvent(Long id) {
        if (!eventRepository.existsById(id)) {
            throw new IllegalArgumentException("Event not found");
        }
        eventRepository.deleteById(id);
    }

    private EventDTO convertToEventDTO(Event event) {
        return new EventDTO(
                event.getEventId(),
                event.getEventName(),
                event.getEventDate(),
                event.getLocation(),
                event.getTotalTickets(),
                event.getAvailableTickets(),
                event.getEventTicketTypes().stream()
                        .map(EventTicketType::getEventTicketTypeId) // Map to IDs
                        .collect(Collectors.toList()));
    }
}