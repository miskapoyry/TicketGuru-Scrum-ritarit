package ticketguru.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "app_user")
public class AppUser {

    // Unique identifier for the user
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false, updatable = false)
    private Long userId;

    // Username of the user
    @Column(name = "username", unique = true)
    private String username;

    // Password hash of the user, cannot be null
    @Column(name = "passwordhash", nullable = false)
    private String passwordHash;

    // Many to one relationship with Role
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    // One-to-many relationship with Event
    @OneToMany(mappedBy = "appUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Event> events = new ArrayList<>();

    // One-to-many relationship with Sale
    @OneToMany(mappedBy = "appUser", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Sale> sales = new ArrayList<>();

    // Default contructor
    public AppUser() {
    }

    // Constructor to initialize all fields
    public AppUser(String username, String passwordHash, Role role, List<Event> events, List<Sale> sales) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.role = role;
        this.events = events != null ? events : new ArrayList<>();
        this.sales = sales != null ? sales : new ArrayList<>();
    }

    // Getters and setters
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
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
