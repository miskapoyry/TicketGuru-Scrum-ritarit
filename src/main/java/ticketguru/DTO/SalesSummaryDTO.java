package ticketguru.DTO;

import java.util.Map;

public class SalesSummaryDTO {
    private int totalSales;
    private double totalRevenue;
    private Map<String, Integer> salesByWeek;
    private Map<String, Double> revenueByWeek;
    private Map<String, Integer> salesByUser;

    public SalesSummaryDTO(int totalSales, double totalRevenue, Map<String, Integer> salesByWeek, Map<String, Double> revenueByWeek, Map<String, Integer> salesByUser) {
        this.totalSales = totalSales;
        this.totalRevenue = totalRevenue;
        this.salesByWeek = salesByWeek;
        this.revenueByWeek = revenueByWeek;
        this.salesByUser = salesByUser;
    }

    // Getters and setters
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

    public Map<String, Integer> getSalesByUser() {
        return salesByUser;
    }

    public void setSalesByUser(Map<String, Integer> salesByUser) {
        this.salesByUser = salesByUser;
    }
}