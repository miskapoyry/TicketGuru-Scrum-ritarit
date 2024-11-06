package ticketguru.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ticketguru.DTO.EventTicketTypeDTO;
import ticketguru.domain.Event;
import ticketguru.domain.EventTicketType;
import ticketguru.domain.TicketType;
import ticketguru.exception.InvalidInputException;
import ticketguru.exception.ResourceNotFoundException;
import ticketguru.repository.EventRepository;
import ticketguru.repository.EventTicketTypeRepository;
import ticketguru.repository.TicketTypeRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventTicketTypeService {

    @Autowired
    private EventTicketTypeRepository eventTicketTypeRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private TicketTypeRepository ticketTypeRepository;

    public List<EventTicketTypeDTO> createEventTicketTypes(List<EventTicketTypeDTO> eventTicketTypeDTOs) {
        List<EventTicketType> eventTicketTypes = eventTicketTypeDTOs.stream().map(dto -> {
            if (dto.getTicketQuantity() <= 0) {
                throw new InvalidInputException("Ticket quantity must be greater than zero");
            }
            if (dto.getPrice() <= 0) {
                throw new InvalidInputException("Price must be greater than zero");
            }
    

            Event event = eventRepository.findById(dto.getEventId())
                    .orElseThrow(() -> new ResourceNotFoundException("Event not found with given ID"));
            TicketType ticketType = ticketTypeRepository.findById(dto.getTicketTypeId())
                    .orElseThrow(() -> new ResourceNotFoundException("TicketType not found with given ID"));

            EventTicketType eventTicketType = new EventTicketType();
            eventTicketType.setEvent(event);
            eventTicketType.setTicketType(ticketType);
            eventTicketType.setTicketQuantity(dto.getTicketQuantity());
            eventTicketType.setPrice(dto.getPrice());

            return eventTicketType;
        }).collect(Collectors.toList());

        List<EventTicketType> savedEventTicketTypes = eventTicketTypeRepository.saveAll(eventTicketTypes);
        return savedEventTicketTypes.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public List<EventTicketTypeDTO> getAllEventTicketTypes() {
        List<EventTicketType> eventTicketTypes = eventTicketTypeRepository.findAll();
        return eventTicketTypes.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public Optional<EventTicketTypeDTO> getEventTicketTypeById(Long id) {
        EventTicketType eventTicketType = eventTicketTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("EventTicketType with ID " + id + " not found"));
        return Optional.of(convertToDto(eventTicketType));
    }

    public Optional<EventTicketTypeDTO> updateEventTicketType(Long id, EventTicketType updatedEventTicketType) {
        if (updatedEventTicketType.getTicketQuantity() <= 0) {
            throw new InvalidInputException("Ticket quantity must be greater than zero");
        }
        if (updatedEventTicketType.getPrice() <= 0) {
            throw new InvalidInputException("Price must be greater than zero");
        }

        EventTicketType existingEventTicketType = eventTicketTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("EventTicketType with ID " + id + " not found"));

        Event event = eventRepository.findById(updatedEventTicketType.getEvent().getEventId())
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with given ID: " + updatedEventTicketType.getEvent().getEventId()));
        TicketType ticketType = ticketTypeRepository.findById(updatedEventTicketType.getTicketType().getTicketTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("TicketType not found with given ID: " + updatedEventTicketType.getTicketType().getTicketTypeId()));

        existingEventTicketType.setEvent(event);
        existingEventTicketType.setTicketType(ticketType);
        existingEventTicketType.setTicketQuantity(updatedEventTicketType.getTicketQuantity());
        existingEventTicketType.setPrice(updatedEventTicketType.getPrice());

        EventTicketType savedEventTicketType = eventTicketTypeRepository.save(existingEventTicketType);
        return Optional.of(convertToDto(savedEventTicketType));
    }
    public boolean deleteEventTicketType(Long id) {
        if (!eventTicketTypeRepository.existsById(id)) {
            return false;
        }
        eventTicketTypeRepository.deleteById(id);
        return true;
    }

    // Utility function to convert entity to DTO
    private EventTicketTypeDTO convertToDto(EventTicketType eventTicketType) {
        return new EventTicketTypeDTO(
                eventTicketType.getEventTicketTypeId(),
                eventTicketType.getEvent().getEventId(), // Mapping only event ID
                eventTicketType.getTicketType().getTicketTypeId(), // Mapping only ticket type ID
                eventTicketType.getTicketQuantity(),
                eventTicketType.getPrice(),
                eventTicketType.getEvent().getEventName(), // Mapping event name
                eventTicketType.getTicketType().getTicketTypeName()); // Mapping ticket type name
    }
}