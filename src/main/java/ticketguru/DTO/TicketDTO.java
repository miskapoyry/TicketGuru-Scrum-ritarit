package ticketguru.DTO;

import java.sql.Timestamp;

public class TicketDTO {

    // Fields
    private Long ticketId;
    private String ticketNumber;
    private Long eventId;
    private Long ticketTypeId;
    private Long saleId;
    private Timestamp saleTimestamp;
    private Boolean isUsed;
    private Timestamp usedTimestamp;
    private int quantity;
    private double price;

    // Default constructor
    public TicketDTO() {
    }

    // Constructor to initialize all fields
    public TicketDTO(Long ticketId, String ticketNumber, Long eventId, Long ticketTypeId, Long saleId,
            Timestamp saleTimestamp, Boolean isUsed, Timestamp usedTimestamp, int quantity, double price) {
        this.ticketId = ticketId;
        this.ticketNumber = ticketNumber;
        this.eventId = eventId;
        this.ticketTypeId = ticketTypeId;
        this.saleId = saleId;
        this.saleTimestamp = saleTimestamp;
        this.isUsed = isUsed;
        this.usedTimestamp = usedTimestamp;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters and setters
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

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public Long getTicketTypeId() {
        return ticketTypeId;
    }

    public void setTicketTypeId(Long ticketTypeId) {
        this.ticketTypeId = ticketTypeId;
    }

    public Long getSaleId() {
        return saleId;
    }

    public void setSaleId(Long saleId) {
        this.saleId = saleId;
    }

    public Timestamp getSaleTimestamp() {
        return saleTimestamp;
    }

    public void setSaleTimestamp(Timestamp saleTimestamp) {
        this.saleTimestamp = saleTimestamp;
    }

    public Boolean isUsed() {
        return isUsed;
    }

    public void setUsed(Boolean isUsed) {
        this.isUsed = isUsed;
    }

    public Timestamp getUsedTimestamp() {
        return usedTimestamp;
    }

    public void setUsedTimestamp(Timestamp usedTimestamp) {
        this.usedTimestamp = usedTimestamp;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}