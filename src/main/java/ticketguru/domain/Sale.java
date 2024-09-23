package ticketguru.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "Sale")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sale_id", nullable = false, updatable = false)
    private Long saleId;
    
    @Column(name = "ticket_id", nullable = false)
    private Long ticketId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sold_by", nullable = false)
    private AppUser soldBy;

    @Column(name = "sale_timestamp", nullable = false)
    private String saleTimestamp;

    @Column(name = "payment_method", nullable = false)
    private String paymentMethod;

    @Column(name = "total_price", nullable = false)
    private int totalPrice;

    public Sale() {
    }

    public Sale(Long ticketId, AppUser soldBy, String saleTimestamp, String paymentMethod, int totalPrice) {
        this.ticketId = ticketId;
        this.soldBy = soldBy;
        this.saleTimestamp = saleTimestamp;
        this.paymentMethod = paymentMethod;
        this.totalPrice = totalPrice;
    }

    public Long getSaleId() {
        return saleId;
    }

    public void setSaleId(Long saleId) {
        this.saleId = saleId;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
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

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
    
}
