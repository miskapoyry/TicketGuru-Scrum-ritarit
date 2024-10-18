package ticketguru.domain;

import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "event")
public class Event {

    // Unique identifier for the event
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id", nullable = false, updatable = false)
    private Long eventId;

    // Many to one relationship with AppUser
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser appUser;

    // Name of the event
    @Column(name = "event_name", nullable = false)
    private String eventName;

    // Date and time of the event
    @Column(name = "event_date", nullable = false)
    private Timestamp eventDate;

    // Location of the event
    @Column(name = "location", nullable = false)
    private String location;

    // Total number of tickets available for the event
    @Column(name = "total_tickets", nullable = false)
    private int totalTickets;

    // Number of ticket available for purchase
    @Column(name = "available_tickets", nullable = false)
    private int availableTickets;

    @OneToMany(mappedBy = "event", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<EventTicketType> eventTicketTypes;
    // Default constructor
    public Event() {
    }

    // Constructor to initialize only eventId
    public Event(Long eventId) {
        this.eventId = eventId;
    }

    // Constructor to initialize all fields
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

    // Getters and setters
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