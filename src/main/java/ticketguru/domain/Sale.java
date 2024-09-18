package ticketguru.domain;

import jakarta.persistence.*;

@Entity

public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="sale_id", nullable = false, updatable = false)
    private Long eventId;
    
    @Column(name="ticket_id", nullable = false)
    private Long ticketId;
}
