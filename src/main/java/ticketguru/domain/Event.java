package ticketguru.domain;

import java.util.*;
import java.sql.Timestamp;

import jakarta.persistence.*;

@Entity
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="event_id", nullable = false, updatable = false)
    private Long eventId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser appUser;

    @Column(name="event_name", nullable = false)
    private String eventName;

    @Column(name="event_date", nullable = false)
    private Timestamp eventDate;

    @Column(name="location", nullable = false)
    private String location;

    @Column(name="total_tickets", nullable = false)
    private int totalTickets;

    @Column(name="available_tickets", nullable = false)
    private int availableTickets;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EventTicketType> eventTicketTypes;

    public Event() {
    }

    public Event(AppUser appUser, String eventName, Timestamp eventDate, String location, int totalTickets,
            int availableTickets, List<EventTicketType> eventTicketTypes) {
        this.appUser = appUser;
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.location = location;
        this.totalTickets = totalTickets;
        this.availableTickets = availableTickets;
        this.eventTicketTypes = eventTicketTypes;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
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

    public List<EventTicketType> getEventTicketTypes() {
        return eventTicketTypes;
    }

    public void setEventTicketTypes(List<EventTicketType> eventTicketTypes) {
        this.eventTicketTypes = eventTicketTypes;
    }
}
