package ticketguru.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public class AppUserDTO {

    private Long userId;

    @NotBlank(message = "Username cannot be blank")
    private String username;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String passwordHash;

    @NotNull(message = "Role ID cannot be null")
    @Min(value = 1, message = "Role ID must be a positive number")
    private Long roleId;

    private List<Long> eventIds; // List of event IDs associated with this user
    private List<Long> saleIds; // List of sale IDs associated with this user

    public AppUserDTO() {}

    public AppUserDTO(Long userId, String username, String passwordHash, Long roleId, List<Long> eventIds, List<Long> saleIds) {
        this.userId = userId;
        this.username = username;
        this.passwordHash = passwordHash;
        this.roleId = roleId;
        this.eventIds = eventIds;
        this.saleIds = saleIds;
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

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public List<Long> getEventIds() {
        return eventIds;
    }

    public void setEventIds(List<Long> eventIds) {
        this.eventIds = eventIds;
    }

    public List<Long> getSaleIds() {
        return saleIds;
    }

    public void setSaleIds(List<Long> saleIds) {
        this.saleIds = saleIds;
    }
}