package ticketguru.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "appUser")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id", nullable = false, updatable = false)
    private Long userId;

    @Column(name="username",unique = true)
    private String username;

    @Column(name= "passwordhash" ,nullable = false)
    private String passwordHash;

    @Column(name = "userrole", nullable = false)
    private String role;

    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Event> events = new ArrayList<>();

    @OneToMany(mappedBy = "soldBy", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Sale> sales = new ArrayList<>();

    public AppUser() {
    }

    public AppUser(String username, String passwordHash, String role, List<Event> events,
            List<Sale> sales) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.role = role;
        this.events = events;
        this.sales = sales;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }
    
}
