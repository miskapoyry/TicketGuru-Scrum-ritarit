package ticketguru.DTO;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import ticketguru.exception.InvalidInputException;

public class EventDTO {

    private Long eventId;

    @NotNull(message = "User ID cannot be null")
    private Long userId;

    @NotEmpty(message = "Event name cannot be empty")
    @Size(max = 100, message = "Event name can be at most 100 characters long")
    private String eventName;

    @NotNull(message = "Event date cannot be null")
    @Future(message = "Event date must be in the future")
    private Timestamp eventDate;

    @NotEmpty(message = "Location cannot be empty")
    @Size(max = 150, message = "Location can be at most 150 characters long")
    private String location;

    @Min(value = 1, message = "Total tickets must be at least 1")
    private int totalTickets;

    @Min(value = 0, message = "Available tickets cannot be negative")
    private int availableTickets;

    // Lipputyyppien nimet ja hinnat
    @Size(min = 1, message = "Event must contain at least one ticket type")
    @Valid
    private List<EventTicketTypeDTO> eventTicketTypes;

    public EventDTO() {
    }

    public EventDTO(Long eventId, @NotNull(message = "User ID cannot be null") Long userId,
            @NotEmpty(message = "Event name cannot be empty") @Size(max = 100, message = "Event name can be at most 100 characters long") String eventName,
            @NotNull(message = "Event date cannot be null") @Future(message = "Event date must be in the future") Timestamp eventDate,
            @NotEmpty(message = "Location cannot be empty") @Size(max = 150, message = "Location can be at most 150 characters long") String location,
            @Min(value = 1, message = "Total tickets must be at least 1") int totalTickets,
            @Min(value = 0, message = "Available tickets cannot be negative") int availableTickets,
            List<EventTicketTypeDTO> eventTicketTypes) {
        this.eventId = eventId;
        this.userId = userId;
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.location = location;
        this.totalTickets = totalTickets;
        this.availableTickets = availableTickets;
        this.eventTicketTypes = eventTicketTypes;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Timestamp getEventDate() {
        return eventDate;
    }

    public void setEventDate(Timestamp eventDate) {
        this.eventDate = eventDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getTotalTickets() {
        return totalTickets;
    }

    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets;
    }

    public int getAvailableTickets() {
        return availableTickets;
    }

    public void setAvailableTickets(int availableTickets) {
        this.availableTickets = availableTickets;
    }

    public List<EventTicketTypeDTO> getEventTicketTypes() {
        return eventTicketTypes;
    }

    public void setEventTicketTypes(List<EventTicketTypeDTO> eventTicketTypes) {
        this.eventTicketTypes = eventTicketTypes;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void validateAvailableTickets() {
        if (availableTickets > totalTickets) {
            throw new InvalidInputException("Available tickets cannot be greater than total tickets");
        }
    }
}