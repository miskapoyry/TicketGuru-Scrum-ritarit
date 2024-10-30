package ticketguru.DTO;

public class RoleDTO {

    private Long roleId;
    private String roleName;

    // Default constructor
    public RoleDTO() {
    }

    // Constructor to initialize all fields
    public RoleDTO(Long roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
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
}