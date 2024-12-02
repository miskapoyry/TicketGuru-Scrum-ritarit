package ticketguru.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import ticketguru.domain.EventTicketType;

public class EventTicketTypeDTO {

    private Long eventTicketTypeId;

    @NotNull(message = "Event ID cannot be null")
    private Long eventId;

    @NotNull(message = "Ticket Type ID cannot be null")
    private Long ticketTypeId;

    @Min(value = 1, message = "Ticket quantity must be at least 1")
    private int ticketQuantity;

    @Positive(message = "Price must be positive")
    private double price;

    private String eventName;
    private String ticketTypeName;

    public EventTicketTypeDTO() {}

    public EventTicketTypeDTO(Long eventTicketTypeId, Long eventId, Long ticketTypeId, int ticketQuantity, double price, String eventName, String ticketTypeName) {
        this.eventTicketTypeId = eventTicketTypeId;
        this.eventId = eventId;
        this.ticketTypeId = ticketTypeId;
        this.ticketQuantity = ticketQuantity;
        this.price = price;
        this.eventName = eventName;
        this.ticketTypeName = ticketTypeName;
    }

    public EventTicketTypeDTO(EventTicketType eventTicketType) {
        this.eventTicketTypeId = eventTicketType.getEventTicketTypeId();
        this.eventId = eventTicketType.getEvent().getEventId();
        this.ticketTypeId = eventTicketType.getTicketType().getTicketTypeId();
        this.ticketQuantity = eventTicketType.getTicketQuantity();
        this.price = eventTicketType.getPrice();
        this.eventName = eventTicketType.getEvent().getEventName();
        this.ticketTypeName = eventTicketType.getTicketType().getTicketTypeName();
    }

    // Getters and setters
    public Long getEventTicketTypeId() {
        return eventTicketTypeId;
    }

    public void setEventTicketTypeId(Long eventTicketTypeId) {
        this.eventTicketTypeId = eventTicketTypeId;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public Long getTicketTypeId() {
        return ticketTypeId;
    }

    public void setTicketTypeId(Long ticketTypeId) {
        this.ticketTypeId = ticketTypeId;
    }

    public int getTicketQuantity() {
        return ticketQuantity;
    }

    public void setTicketQuantity(int ticketQuantity) {
        this.ticketQuantity = ticketQuantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getTicketTypeName() {
        return ticketTypeName;
    }

    public void setTicketTypeName(String ticketTypeName) {
        this.ticketTypeName = ticketTypeName;
    }
}