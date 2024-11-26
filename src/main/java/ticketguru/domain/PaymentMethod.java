package ticketguru.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "payment_method")
public class PaymentMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_method_id", nullable = false, updatable = false)
    private Long paymentMethodId;

    @Column(name = "payment_method_name", nullable = false, unique = true)
    private String paymentMethodName;

    // One to many relationship with Sale
    @OneToMany(mappedBy = "paymentMethod")
    private List<Sale> sales = new ArrayList<>();

    public PaymentMethod(Long paymentMethodId, String paymentMethodName, List<Sale> sales) {
        this.paymentMethodId = paymentMethodId;
        this.paymentMethodName = paymentMethodName;
        this.sales = sales;
    }

    public PaymentMethod(String paymentMethodName) {
        this.paymentMethodName = paymentMethodName;
    }

    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }

    public Long getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(Long paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public String getPaymentMethodName() {
        return paymentMethodName;
    }

    public void setPaymentMethodName(String paymentMethodName) {
        this.paymentMethodName = paymentMethodName;
    }

    public PaymentMethod() {

    }

}
