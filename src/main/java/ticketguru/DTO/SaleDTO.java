package ticketguru.DTO;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

public class SaleDTO {

    // Fields
    private Long saleId;
    @NotBlank(message = "Payment method is required")
    private String paymentMethod;
    @Min(value = 0, message = "Price cannot be negative")
    private double totalPrice;
    private Timestamp saleTimestamp;
    @NotNull(message = "UserID is required")
    private Long userId;
    @NotNull(message = "Sale must be assigned with tickets")
    @Size(min = 1, message = "Sale must contain at least one ticket")
    @Valid
    private List<TicketDTO> tickets;

    // Constructor to initialize all fields
    public SaleDTO(Long saleId, String paymentMethod, double totalPrice, Timestamp saleTimestamp, Long userId,
            List<TicketDTO> tickets) {
        this.saleId = saleId;
        this.paymentMethod = paymentMethod;
        this.totalPrice = totalPrice;
        this.saleTimestamp = saleTimestamp;
        this.userId = userId;
        this.tickets = tickets;
    }

    // Getters and setters
    public Long getSaleId() {
        return saleId;
    }

    public void setSaleId(Long saleId) {
        this.saleId = saleId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    // Set total price to 2 decimal places
    public void setTotalPrice(double totalPrice) {
        BigDecimal bd = BigDecimal.valueOf(totalPrice);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        this.totalPrice = bd.doubleValue();
    }

    public Timestamp getSaleTimestamp() {
        return saleTimestamp;
    }

    public void setSaleTimestamp(Timestamp saleTimestamp) {
        this.saleTimestamp = saleTimestamp;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<TicketDTO> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketDTO> tickets) {
        this.tickets = tickets;
    }
}