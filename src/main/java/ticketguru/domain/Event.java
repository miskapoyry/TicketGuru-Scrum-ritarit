package ticketguru.domain;

import jakarta.persistence.*;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="event_id", nullable = false, updatable = false)
    private Long eventId;

    @Column(name="created_by", nullable = false)
    private AppUser createdBy; 

    @Column(name="event_name", nullable = false)
    private String eventName;

    @Column(name="event_date", nullable = false)
    private String eventDate;

    @Column(name="location", nullable = false)
    private String location;

    @Column(name="total_tickets", nullable = false)
    private Integer totalTicket;

    @Column(name="available_tickets", nullable = false)
    private Integer availableTickets;

    public Event() {
    }

    public Event(Long eventId, AppUser createdBy, String eventName, String eventDate, String location,
            Integer totalTicket, Integer availableTickets) {
        this.eventId = eventId;
        this.createdBy = createdBy;
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.location = location;
        this.totalTicket = totalTicket;
        this.availableTickets = availableTickets;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public AppUser getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(AppUser createdBy) {
        this.createdBy = createdBy;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getTotalTicket() {
        return totalTicket;
    }

    public void setTotalTicket(Integer totalTicket) {
        this.totalTicket = totalTicket;
    }

    public Integer getAvailableTickets() {
        return availableTickets;
    }

    public void setAvailableTickets(Integer availableTickets) {
        this.availableTickets = availableTickets;
    }
    
}
