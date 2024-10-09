package ticketguru.DTO;

public class TicketSummaryDTO {
    private Long ticketId;
    private int quantity;
    private double price;
    private Long eventId;

    public TicketSummaryDTO(Long ticketId, int quantity, double price, Long eventId) {
        this.ticketId = ticketId;
        this.quantity = quantity;
        this.price = price;
        this.eventId = eventId;
    }

    // Getters and setters
    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
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

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

}
