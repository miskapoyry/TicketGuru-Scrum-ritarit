package ticketguru.DTO;

import java.sql.Timestamp;
import java.util.List;

public class SaleSummaryDTO {

    // Fields
    private Long saleId;
    private String paymentMethod;
    private double totalPrice;
    private Timestamp saleTimestamp;
    private Long userId;
    private List<TicketSummaryDTO> ticketSummaries;
    private String message;

    // Constructor to initialize all fields
    public SaleSummaryDTO(Long saleId, String paymentMethod, double totalPrice, Timestamp saleTimestamp, Long userId,
            List<TicketSummaryDTO> ticketSummaries) {
        this.saleId = saleId;
        this.paymentMethod = paymentMethod;
        this.totalPrice = totalPrice;
        this.saleTimestamp = saleTimestamp;
        this.userId = userId;
        this.ticketSummaries = ticketSummaries;
    }

    // Getters and setters
    public Long getSaleId() {
        return saleId;
    }

    public SaleSummaryDTO(String message) {
        this.message = message;
    }

    // Getters and setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
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

    public List<TicketSummaryDTO> getTicketSummaries() {
        return ticketSummaries;
    }

    public void setTicketSummaries(List<TicketSummaryDTO> ticketSummaries) {
        this.ticketSummaries = ticketSummaries;
    }
}