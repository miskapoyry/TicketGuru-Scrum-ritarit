package ticketguru.DTO;

public class EventReportDTO {

    private String eventName;
    private String ticketTypeName;
    private int ticketsSold;
    private double totalRevenue;

    public EventReportDTO() {}

    public EventReportDTO(String eventName, String ticketTypeName, int ticketsSold, double totalRevenue) {
        this.eventName = eventName;
        this.ticketTypeName = ticketTypeName;
        this.ticketsSold = ticketsSold;
        this.totalRevenue = totalRevenue;
    }

    // Getters and setters
    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getTicketTypeName() {
        return ticketTypeName;
    }

    public void setTicketTypeName(String ticketTypeName) {
        this.ticketTypeName = ticketTypeName;
    }

    public int getTicketsSold() {
        return ticketsSold;
    }

    public void setTicketsSold(int ticketsSold) {
        this.ticketsSold = ticketsSold;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}