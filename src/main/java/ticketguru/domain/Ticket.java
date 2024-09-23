package ticketguru.domain;

import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ticket_id", nullable = false, updatable = false)
    private Long ticketId;

    @Column(name = "ticket_number", nullable = false, unique = true)
    private String ticketNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="event_id", nullable = false)
    private Event event;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ticket_type_id", nullable = false)
    private TicketType ticketType;

    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SaleTicket> saleTickets;

    @Column(name = "sale_timestamp", nullable = false)
    private Timestamp saleTimestamp;

    @Column(name = "is_used", nullable = false)
    private boolean isUsed;

    @Column(name = "used_timestamp")
    private Timestamp usedTimestamp;

    public Ticket() {
    }

    public Ticket(String ticketNumber, Event event, TicketType ticketType, List<SaleTicket> saleTickets,
            Timestamp saleTimestamp, boolean isUsed, Timestamp usedTimestamp) {
        this.ticketNumber = ticketNumber;
        this.event = event;
        this.ticketType = ticketType;
        this.saleTickets = saleTickets;
        this.saleTimestamp = saleTimestamp;
        this.isUsed = isUsed;
        this.usedTimestamp = usedTimestamp;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
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

    public List<SaleTicket> getSaleTickets() {
        return saleTickets;
    }

    public void setSaleTickets(List<SaleTicket> saleTickets) {
        this.saleTickets = saleTickets;
    }

    public Timestamp getSaleTimestamp() {
        return saleTimestamp;
    }

    public void setSaleTimestamp(Timestamp saleTimestamp) {
        this.saleTimestamp = saleTimestamp;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean isUsed) {
        this.isUsed = isUsed;
    }

    public Timestamp getUsedTimestamp() {
        return usedTimestamp;
    }

    public void setUsedTimestamp(Timestamp usedTimestamp) {
        this.usedTimestamp = usedTimestamp;
    }
}
