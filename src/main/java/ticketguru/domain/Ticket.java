package ticketguru.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "Ticket")
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="sold_by", nullable = false)
    private AppUser soldBy;

    @Column(name = "sale_timestamp", nullable = false)
    private String saleTimestamp;

    @Column(name = "is_used", nullable = false)
    private boolean isUsed;

    public Ticket() {
    }

    public Ticket(Long ticketId, String ticketNumber, Event event, TicketType ticketType, AppUser soldBy,
            String saleTimestamp, boolean isUsed) {
        this.ticketId = ticketId;
        this.ticketNumber = ticketNumber;
        this.event = event;
        this.ticketType = ticketType;
        this.soldBy = soldBy;
        this.saleTimestamp = saleTimestamp;
        this.isUsed = isUsed;
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

    public AppUser getSoldBy() {
        return soldBy;
    }

    public void setSoldBy(AppUser soldBy) {
        this.soldBy = soldBy;
    }

    public String getSaleTimestamp() {
        return saleTimestamp;
    }

    public void setSaleTimestamp(String saleTimestamp) {
        this.saleTimestamp = saleTimestamp;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean isUsed) {
        this.isUsed = isUsed;
    }
}
