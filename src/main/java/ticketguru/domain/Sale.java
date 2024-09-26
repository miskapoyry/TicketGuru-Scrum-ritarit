package ticketguru.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "sale")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sale_id", nullable = false, updatable = false)
    private Long saleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser appUser;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SaleTicket> saleTickets;

    @Column(name = "sale_timestamp", nullable = false)
    private Timestamp saleTimestamp;

    @Column(name = "payment_method", nullable = false)
    private String paymentMethod;

    @Column(name = "total_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalPrice;

    public Sale() {
    }

    public Sale(AppUser appUser, List<SaleTicket> saleTickets, Timestamp saleTimestamp, String paymentMethod,
            BigDecimal totalPrice) {
        this.appUser = appUser;
        this.saleTickets = saleTickets;
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

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
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

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
