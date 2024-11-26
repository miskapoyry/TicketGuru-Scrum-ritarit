package ticketguru.domain;

import java.sql.Timestamp;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
@Table(name = "sale")
public class Sale {

    // Unique identifier for the sale
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sale_id", nullable = false, updatable = false)
    private Long saleId;

    // Many to one relationship with AppUser
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser appUser;

    // One to many relationship with Ticket
    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)
    private List<Ticket> tickets = new ArrayList<>();

    // Date and time of the sale
    @Column(name = "sale_timestamp", nullable = false)
    private Timestamp saleTimestamp;

    // Payment method used for the sale, many to one relationship
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_method_id", nullable = false)

    private PaymentMethod paymentMethod;

    // Total price of the sale
    @Column(name = "total_price", nullable = false, scale = 2)
    private double totalPrice;

    // Default constructor
    public Sale() {
    }

    // Constructor to initialize all fields
    public Sale(AppUser appUser, Timestamp saleTimestamp, PaymentMethod paymentMethod, double totalPrice) {
        this.appUser = appUser;
        this.saleTimestamp = saleTimestamp;
        this.paymentMethod = paymentMethod;
        this.totalPrice = totalPrice;
    }

    // Getters and setters
    public Long getSaleId() {
        return saleId;
    }

    public void setSaleId(Long saleId) {
        this.saleId = saleId;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Timestamp getSaleTimestamp() {
        return saleTimestamp;
    }

    public void setSaleTimestamp(Timestamp saleTimestamp) {
        this.saleTimestamp = saleTimestamp;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
