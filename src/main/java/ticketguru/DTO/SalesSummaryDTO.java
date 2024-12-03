package ticketguru.DTO;

import java.util.Map;

public class SalesSummaryDTO {

    private String eventName;
    private int totalSales;
    private double totalRevenue;
    private Map<String, Integer> salesByWeek;
    private Map<String, Double> revenueByWeek;
    private Map<Long, Map<String, TicketTypeSummary>> salesByUserAndTicketType;
    private Map<String, Integer> salesByTicketType;
    private Map<String, Double> revenueByTicketType;

    public SalesSummaryDTO(String eventName, int totalSales, double totalRevenue, Map<String, Integer> salesByWeek, Map<String, Double> revenueByWeek, Map<Long, Map<String, TicketTypeSummary>> salesByUserAndTicketType, Map<String, Integer> salesByTicketType, Map<String, Double> revenueByTicketType) {
        this.eventName = eventName;
        this.totalSales = totalSales;
        this.totalRevenue = totalRevenue;
        this.salesByWeek = salesByWeek;
        this.revenueByWeek = revenueByWeek;
        this.salesByUserAndTicketType = salesByUserAndTicketType;
        this.salesByTicketType = salesByTicketType;
        this.revenueByTicketType = revenueByTicketType;
    }

    // Getters and setters
    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public int getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(int totalSales) {
        this.totalSales = totalSales;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public Map<String, Integer> getSalesByWeek() {
        return salesByWeek;
    }

    public void setSalesByWeek(Map<String, Integer> salesByWeek) {
        this.salesByWeek = salesByWeek;
    }

    public Map<String, Double> getRevenueByWeek() {
        return revenueByWeek;
    }

    public void setRevenueByWeek(Map<String, Double> revenueByWeek) {
        this.revenueByWeek = revenueByWeek;
    }

    public Map<Long, Map<String, TicketTypeSummary>> getSalesByUserAndTicketType() {
        return salesByUserAndTicketType;
    }

    public void setSalesByUserAndTicketType(Map<Long, Map<String, TicketTypeSummary>> salesByUserAndTicketType) {
        this.salesByUserAndTicketType = salesByUserAndTicketType;
    }

    public Map<String, Integer> getSalesByTicketType() {
        return salesByTicketType;
    }

    public void setSalesByTicketType(Map<String, Integer> salesByTicketType) {
        this.salesByTicketType = salesByTicketType;
    }

    public Map<String, Double> getRevenueByTicketType() {
        return revenueByTicketType;
    }

    public void setRevenueByTicketType(Map<String, Double> revenueByTicketType) {
        this.revenueByTicketType = revenueByTicketType;
    }

    public static class TicketTypeSummary {
        private int ticketsSold;
        private double revenue;

        public TicketTypeSummary(int ticketsSold, double revenue) {
            this.ticketsSold = ticketsSold;
            this.revenue = revenue;
        }

        // Getters and setters
        public int getTicketsSold() {
            return ticketsSold;
        }

        public void setTicketsSold(int ticketsSold) {
            this.ticketsSold = ticketsSold;
        }

        public double getRevenue() {
            return revenue;
        }

        public void setRevenue(double revenue) {
            this.revenue = revenue;
        }
    }
}