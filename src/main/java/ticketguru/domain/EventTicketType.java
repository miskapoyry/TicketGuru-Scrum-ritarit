package ticketguru.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "event_ticket_type")
public class EventTicketType {

    // Unique identifier for the event ticket type
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_ticket_type_id", nullable = false, updatable = false)
    private Long eventTicketTypeId;

    // Many to one relationship with Event
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    // Many to one relationship with TicketType
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ticket_type_id", nullable = false)
    private TicketType ticketType;

    // Number of tickets available for the event ticket type
    @Column(name = "ticket_quantity", nullable = true)
    private int ticketQuantity;

    // Price of the event ticket type
    @Column(name = "price", nullable = false)
    private double price;

    // Default constructor
    public EventTicketType() {
    }

    // Constructor to initialize all fields
    public EventTicketType(Event event, TicketType ticketType, int ticketQuantity,
            double price) {
        this.event = event;
        this.ticketType = ticketType;
        this.ticketQuantity = ticketQuantity;
        this.price = price;
    }

    // Getters and setters
    public Long getEventTicketTypeId() {
        return eventTicketTypeId;
    }

    public void setEventTicketTypeId(Long eventTicketTypeId) {
        this.eventTicketTypeId = eventTicketTypeId;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
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
}
