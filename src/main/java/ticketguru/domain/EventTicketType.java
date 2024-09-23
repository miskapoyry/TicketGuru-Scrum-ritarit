package ticketguru.domain;

import jakarta.persistence.*;

@Entity
public class EventTicketType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="event_ticket_type_id", nullable = false, updatable = false)
    private Long eventTicketTypeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ticket_type_id", nullable = false)
    private TicketType ticketType;

    @Column(name = "ticket_quantity", nullable = false)
    private int ticketQuantity;

    @Column(name = "price", nullable = false)
    private double price;

    

}
