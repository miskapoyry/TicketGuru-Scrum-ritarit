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

    @Column(name="sold_by", nullable = false)
    private Long Id;

    @Column(name="sale_timestamp", nullable = false)
    private String saleTimestamp;

    @Column(name="payment_method", nullable = false)
    private String paymentMethod;

    @Column(name="total_price", nullable = false)
    private int totalPrice;

    public Sale() {
    }

    public Sale(Long eventId, Long ticketId, Long id, String saleTimestamp, String paymentMethod, int totalPrice) {
        this.eventId = eventId;
        this.ticketId = ticketId;
        Id = id;
        this.saleTimestamp = saleTimestamp;
        this.paymentMethod = paymentMethod;
        this.totalPrice = totalPrice;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
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
