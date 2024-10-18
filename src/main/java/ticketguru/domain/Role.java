package ticketguru.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "role")
public class Role {

    // Unique identifier for the role
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", nullable = false, updatable = false)
    private Long roleId;

    // Name of the role
    @Column(name = "role_name", nullable = false)
    private String roleName;

    // One to many relationship with AppUser
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<AppUser> users;

    // Default constructor
    public Role() {
    }

    // Constructor to initialize all fields
    public Role(Long roleId, String roleName, List<AppUser> users) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.users = users;
    }

    // Getters and setters
    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<AppUser> getUsers() {
        return users;
    }

    public void setUsers(List<AppUser> users) {
        this.users = users;
    }
}
