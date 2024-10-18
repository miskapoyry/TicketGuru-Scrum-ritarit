package ticketguru.DTO;

import java.sql.Timestamp;
import java.util.List;

public class EventDTO {
    private Long eventId;
    private String eventName;
    private Timestamp eventDate;
    private String location;
    private int totalTickets;
    private int availableTickets;
    private List<Long> eventTicketTypeIds;

    public EventDTO() {
    }

    public EventDTO(Long eventId, String eventName, Timestamp eventDate, String location, int totalTickets,
            int availableTickets, List<Long> eventTicketTypeIds) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.location = location;
        this.totalTickets = totalTickets;
        this.availableTickets = availableTickets;
        this.eventTicketTypeIds = eventTicketTypeIds;
    }

    // Getters and setters
    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
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

    public List<Long> getEventTicketTypeIds() {
        return eventTicketTypeIds;
    }

    public void setEventTicketTypeIds(List<Long> eventTicketTypeIds) {
        this.eventTicketTypeIds = eventTicketTypeIds;
    }
}
