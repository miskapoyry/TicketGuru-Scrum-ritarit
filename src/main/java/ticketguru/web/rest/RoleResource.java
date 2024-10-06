package ticketguru.web.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ticketguru.domain.Role;
import ticketguru.repository.RoleRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/role")
public class RoleResource {

    private final RoleRepository roleRepository;

    public RoleResource(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @PostMapping("")
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        if (role.getRoleId() != null) {
            return ResponseEntity.badRequest().body(role);
        }
        role = roleRepository.save(role);
        return ResponseEntity.ok(role);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable Long id, @RequestBody Role role) {
        Optional<Role> existingRoleOptional = roleRepository.findById(id);

        if (existingRoleOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Role existingRole = existingRoleOptional.get();
        existingRole.setRoleName(role.getRoleName());
        existingRole.setUsers(role.getUsers()); // Optional: update users if needed

        Role updatedRole = roleRepository.save(existingRole);
        return ResponseEntity.ok(updatedRole);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> getRole(@PathVariable Long id) {
        Optional<Role> role = roleRepository.findById(id);
        return ResponseEntity.of(role);
    }

    @GetMapping("")
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        if (!roleRepository.existsById(id)) {
            return ResponseEntity.notFound().build(); // Return 404 if role not found
        }
        roleRepository.deleteById(id); // Perform the delete operation
        return ResponseEntity.noContent().build(); // Return 204 No Content
    }
}