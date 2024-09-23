package ticketguru.domain;

import java.util.*;

import jakarta.persistence.*;

@Entity
@Table(name = "ticketType")
public class TicketType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ticket_type_id", nullable = false, updatable = false)
    private Long ticketTypeId;

    @Column(name = "ticket_type_name", nullable = false)
    private String ticketTypeName;

    @OneToMany(mappedBy = "ticketType", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EventTicketType> eventTicketTypes;

    public TicketType() {
    }

    public TicketType(Long ticketTypeId, String ticketTypeName, List<EventTicketType> eventTicketTypes) {
        this.ticketTypeId = ticketTypeId;
        this.ticketTypeName = ticketTypeName;
        this.eventTicketTypes = eventTicketTypes;
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

    public List<EventTicketType> getEventTicketTypes() {
        return eventTicketTypes;
    }

    public void setEventTicketTypes(List<EventTicketType> eventTicketTypes) {
        this.eventTicketTypes = eventTicketTypes;
    }
}
