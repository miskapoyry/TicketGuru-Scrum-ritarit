package ticketguru.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "ticket_type")
public class TicketType {

    // Unique identifier for the ticket type
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_type_id", nullable = false, updatable = false)
    private Long ticketTypeId;

    // Name of the ticket type
    @Column(name = "ticket_type_name", nullable = false)
    private String ticketTypeName;


    
    // Default constructor
    public TicketType() {
    }

    // Constructor to initialize all fields
    public TicketType(String ticketTypeName) {
        this.ticketTypeName = ticketTypeName;
    }

    // Constructor with ticketTypeId
    public TicketType(Long ticketTypeId) {
        this.ticketTypeId = ticketTypeId;
    }

    //  Getters and setters
    public Long getTicketTypeId() {
        return ticketTypeId;
    }

    public void setTicketTypeId(Long ticketTypeId) {
        this.ticketTypeId = ticketTypeId;
    }

    public String getTicketTypeName() {
        return ticketTypeName;
    }

    public void setTicketTypeName(String ticketTypeName) {
        this.ticketTypeName = ticketTypeName;
    }
}
