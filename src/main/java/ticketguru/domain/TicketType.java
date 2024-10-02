package ticketguru.domain;

import java.util.*;

import jakarta.persistence.*;

@Entity
@Table(name = "ticket_type")
public class TicketType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ticket_type_id", nullable = false, updatable = false)
    private Long ticketTypeId;

    @Column(name = "ticket_type_name", nullable = false)
    private String ticketTypeName;

    //@OneToMany(mappedBy = "ticketType", cascade = CascadeType.ALL, orphanRemoval = true)
    //private List<EventTicketType> eventTicketTypes;
    
    //@OneToMany(mappedBy = "ticketType", cascade = CascadeType.ALL, orphanRemoval = true)
    //private List<Ticket> tickets;

    public TicketType() {
    }

    public TicketType(String ticketTypeName) {
        this.ticketTypeName = ticketTypeName;
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

//    public List<EventTicketType> getEventTicketTypes() {
//         return eventTicketTypes;
//     }

//     public void setEventTicketTypes(List<EventTicketType> eventTicketTypes) {
//      this.eventTicketTypes = eventTicketTypes;
//     }

    // public List<Ticket> getTickets() {
    //     return tickets;
    // }

    // public void setTickets(List<Ticket> tickets) {
    //     this.tickets = tickets;
    // }
}
