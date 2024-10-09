package ticketguru.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ticketguru.domain.Role;
import ticketguru.repository.RoleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role createRole(Role role) {
        if (role.getRoleId() != null) {
            throw new IllegalArgumentException("New role cannot already have an ID");
        }
        return roleRepository.save(role);
    }

    public Role updateRole(Long id, Role role) {
        Optional<Role> existingRoleOptional = roleRepository.findById(id);

        if (existingRoleOptional.isEmpty()) {
            throw new IllegalArgumentException("Role not found");
        }

        Role existingRole = existingRoleOptional.get();
        existingRole.setRoleName(role.getRoleName());
        existingRole.setUsers(role.getUsers()); // Optional: update users if needed

        return roleRepository.save(existingRole);
    }

    public Optional<Role> getRole(Long id) {
        return roleRepository.findById(id);
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public void deleteRole(Long id) {
        if (!roleRepository.existsById(id)) {
            throw new IllegalArgumentException("Role not found");
        }
        roleRepository.deleteById(id);
    }
}
