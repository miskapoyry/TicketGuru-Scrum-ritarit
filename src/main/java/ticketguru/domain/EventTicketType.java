package ticketguru.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "event_ticket_type")
public class EventTicketType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_ticket_type_id", nullable = false, updatable = false)
    private Long eventTicketTypeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ticket_type_id", nullable = false)
    private TicketType ticketType;

    @Column(name = "ticket_quantity", nullable = true)
    private int ticketQuantity;

    @Column(name = "price", nullable = false)
    private double price;

    public EventTicketType() {}

    public EventTicketType(Event event, TicketType ticketType, int ticketQuantity, double price) {
        this.event = event;
        this.ticketType = ticketType;
        this.ticketQuantity = ticketQuantity;
        this.price = price;
    }

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