package ticketguru.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ticketguru.DTO.EventDTO;
import ticketguru.domain.AppUser;
import ticketguru.domain.Event;
import ticketguru.repository.AppUserRepository;
import ticketguru.repository.EventRepository;
import ticketguru.repository.EventTicketTypeRepository;
import ticketguru.repository.TicketTypeRepository;
import ticketguru.domain.EventTicketType;
import ticketguru.domain.TicketType;
import ticketguru.exception.InvalidInputException;
import ticketguru.exception.ResourceNotFoundException;

import java.util.stream.Collectors;
import java.util.*;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private EventTicketTypeRepository eventTicketTypeRepository;

    @Autowired
    private TicketTypeRepository ticketTypeRepository;

    public EventDTO createEvent(EventDTO eventDTO) {
        // Hae käyttäjä ID:n perusteella
        AppUser user = appUserRepository.findById(eventDTO.getUserId())
            .orElseThrow(() -> new ResourceNotFoundException("User not found with given ID"));
    
        // Luo uusi Event-objekti DTO:n perusteella
        Event event = new Event();
        event.setEventName(eventDTO.getEventName());
        event.setEventDate(eventDTO.getEventDate());
        event.setLocation(eventDTO.getLocation());
        event.setTotalTickets(eventDTO.getTotalTickets());
        // Tarkistetaan onko available suurempi kuin total (EventDTO). Jos näin on niin palautetaan 400 error invalidinput
        eventDTO.validateAvailableTickets();
        event.setAvailableTickets(eventDTO.getAvailableTickets());
        event.setAppUser(user);  // Aseta käyttäjä ennen tallennusta!
    
        // Tallenna tapahtuma tietokantaan
        Event newEvent = eventRepository.save(event);
    
        // Lisää lipputyypit tapahtumaan
        List<EventTicketType> eventTicketTypes = new ArrayList<>();
        for (Map.Entry<String, Double> entry : eventDTO.getTicketTypes().entrySet()) {
            String ticketTypeName = entry.getKey();
            Double price = entry.getValue();

            // Lähetetään virhe, jos lipputyypin hinta on alle pienempi kuin 0
            if (price < 0) {
                throw new InvalidInputException("Price for ticket type cannot be negative");
            }

            // Hae tai luo uusi TicketType
            TicketType ticketType = ticketTypeRepository.findByTicketTypeName(ticketTypeName)
                    .orElseGet(() -> {
                        TicketType newTicketType = new TicketType();
                        newTicketType.setTicketTypeName(ticketTypeName);
                        return ticketTypeRepository.save(newTicketType);
                    });
    
            // Luo uusi EventTicketType ja aseta hinta
            EventTicketType eventTicketType = new EventTicketType();
            eventTicketType.setEvent(newEvent);
            eventTicketType.setTicketType(ticketType);
            eventTicketType.setPrice(price);
    
            // Lisää EventTicketType-objekti listaan
            eventTicketTypes.add(eventTicketType);
        }
    
        // Tallenna kaikki EventTicketType-objektit
        eventTicketTypeRepository.saveAll(eventTicketTypes);
    
        // Hae päivitetty tapahtuma tietokannasta
        Event updatedEvent = eventRepository.findById(newEvent.getEventId())
            .orElseThrow(() -> new ResourceNotFoundException("Event not found"));
    
        // Pakotetaan eventTicketTypes latautumaan:
        updatedEvent.setEventTicketTypes(eventTicketTypes);
    
        // Palauta EventDTO käyttäen `convertToEventDTO`
        return convertToEventDTO(updatedEvent);
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
        List<Event> events;
        if (eventName != null && location != null) {
            events = eventRepository.findByEventNameContainsIgnoreCaseAndLocationContainsIgnoreCase(eventName,
                    location);
        } else if (eventName != null) {
            events = eventRepository.findByEventNameContainsIgnoreCase(eventName);
        } else if (location != null) {
            events = eventRepository.findByLocationContainsIgnoreCase(location);
        } else {
            events = eventRepository.findAll();
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

        Map<String, Double> ticketPrices = event.getEventTicketTypes().stream()
            .collect(Collectors.toMap(
                eventTicketType -> eventTicketType.getTicketType().getTicketTypeName(),
                EventTicketType::getPrice
            ));
    
        // Palauta EventDTO-olio
        return new EventDTO(
                event.getEventId(),
                event.getAppUser().getUserId(),
                event.getEventName(),
                event.getEventDate(),
                event.getLocation(),
                event.getTotalTickets(),
                event.getAvailableTickets(),
                ticketPrices
        );
    }
    
}