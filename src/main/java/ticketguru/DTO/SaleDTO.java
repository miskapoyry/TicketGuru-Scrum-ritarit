package ticketguru.DTO;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class SaleDTO {

    private Long saleId;
    private String paymentMethod;
    private BigDecimal totalPrice;
    private Timestamp saleTimestamp;
    private Long userId;
    private List<Long> ticketIds;
    
    public SaleDTO(Long saleId, String paymentMethod, BigDecimal totalPrice, Timestamp saleTimestamp, Long userId,
            List<Long> ticketIds) {
        this.saleId = saleId;
        this.paymentMethod = paymentMethod;
        this.totalPrice = totalPrice;
        this.saleTimestamp = saleTimestamp;
        this.userId = userId;
        this.ticketIds = ticketIds;
    }

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

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
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

    public List<Long> getTicketIds() {
        return ticketIds;
    }

    public void setTicketIds(List<Long> ticketIds) {
        this.ticketIds = ticketIds;
    }
}
