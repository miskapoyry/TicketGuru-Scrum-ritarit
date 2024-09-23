package ticketguru.domain;

import java.util.*;

import jakarta.persistence.*;

@Entity
@Table(name = "Event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="event_id", nullable = false, updatable = false)
    private Long eventId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", nullable = false)
    private AppUser createdBy;

    @Column(name="event_name", nullable = false)
    private String eventName;

    @Column(name="event_date", nullable = false)
    private String eventDate;

    @Column(name="location", nullable = false)
    private String location;

    @Column(name="total_tickets", nullable = false)
    private int totalTicket;

    @Column(name="available_tickets", nullable = false)
    private int availableTickets;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticket> tickets;

    public Event() {
    }

    public Event(Long eventId, AppUser createdBy, String eventName, String eventDate, String location, int totalTicket,
            int availableTickets, List<Ticket> tickets) {
        this.eventId = eventId;
        this.createdBy = createdBy;
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.location = location;
        this.totalTicket = totalTicket;
        this.availableTickets = availableTickets;
        this.tickets = tickets;
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

    public int getTotalTicket() {
        return totalTicket;
    }

    public void setTotalTicket(int totalTicket) {
        this.totalTicket = totalTicket;
    }

    public int getAvailableTickets() {
        return availableTickets;
    }

    public void setAvailableTickets(int availableTickets) {
        this.availableTickets = availableTickets;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
    
}
