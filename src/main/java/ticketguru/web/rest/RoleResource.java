package ticketguru.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ticketguru.domain.Role;
import ticketguru.service.RoleService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/role")
public class RoleResource {

    @Autowired
    private RoleService roleService;

    @PostMapping("")
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        try {
            Role createdRole = roleService.createRole(role);
            return ResponseEntity.ok(createdRole);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(role);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable Long id, @RequestBody Role role) {
        try {
            Role updatedRole = roleService.updateRole(id, role);
            return ResponseEntity.ok(updatedRole);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> getRole(@PathVariable Long id) {
        Optional<Role> role = roleService.getRole(id);
        return ResponseEntity.of(role);
    }

    @GetMapping("")
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        try {
            roleService.deleteRole(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}