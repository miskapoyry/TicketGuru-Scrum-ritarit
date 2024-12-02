package ticketguru.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "sale_ticket")
public class SaleTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="sale_ticket_id", nullable = false, updatable = false)
    private Long saleTicketId;

    @ManyToOne
    @JoinColumn(name = "ticket_id", nullable = false)
    private Ticket ticket;

    @ManyToOne
    @JoinColumn(name = "sale_id", nullable = false)
    private Sale sale;

    public SaleTicket() {}

    public SaleTicket(Ticket ticket, Sale sale) {
        this.ticket = ticket;
        this.sale = sale;
    }

    public Long getSaleTicketId() {
        return saleTicketId;
    }

    public void setSaleTicketId(Long saleTicketId) {
        this.saleTicketId = saleTicketId;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }
}