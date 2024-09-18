package ticketguru.domain;

import jakarta.persistence.*;

@Entity
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false, updatable = false)
    private Long Id;

    @Column(name="username",unique = true)
    private String username;

    @Column(name= "passwordhash" ,nullable = false)
    private String passwordHash;

    @Column(name = "userrole", nullable = false)
    private String role;

    public AppUser() {
    }

    public AppUser(Long id, String username, String passwordHash, String role) {
        Id = id;
        this.username = username;
        this.passwordHash = passwordHash;
        this.role = role;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
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
    
}
