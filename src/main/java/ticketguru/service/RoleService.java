package ticketguru.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ticketguru.domain.AppUser;
import ticketguru.domain.Role;
import ticketguru.repository.RoleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    // Create a new role
    public Role createRole(Role role) {
        if (role.getRoleId() != null) {
            throw new IllegalArgumentException("New role cannot already have an ID");
        }
        return roleRepository.save(role);
    }

    // Update an existing role
    public Role updateRole(Long id, Role role) {
        Role existingRole = roleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Role not found"));

        existingRole.setRoleName(role.getRoleName()); // Roolinimen päivitys

        if (role.getUsers() != null) {
            // Tyhjennetään nykyiset
            existingRole.getUsers().clear();

            // Lisätään uudet käyttäjät ja asetetaan roolit
            for (AppUser user : role.getUsers()) {
                user.setRole(existingRole);
                existingRole.getUsers().add(user);
            }
        }

        return roleRepository.save(existingRole);
    }

    // Get a role by ID
    public Optional<Role> getRole(Long id) {
        return roleRepository.findById(id);
    }

    // Get all roles
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    // Delete a role by ID
    public void deleteRole(Long id) {
        if (!roleRepository.existsById(id)) {
            throw new IllegalArgumentException("Role not found");
        }
        roleRepository.deleteById(id);
    }
}