package ticketguru.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "ticket_type")
public class TicketType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_type_id", nullable = false, updatable = false)
    private Long ticketTypeId;

    @Column(name = "ticket_type_name", nullable = false)
    private String ticketTypeName;

    public TicketType() {}

    public TicketType(String ticketTypeName) {
        this.ticketTypeName = ticketTypeName;
    }

    public TicketType(Long ticketTypeId) {
        this.ticketTypeId = ticketTypeId;
    }

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