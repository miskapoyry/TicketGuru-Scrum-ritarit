package ticketguru.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ticketguru.DTO.AppUserDTO;
import ticketguru.domain.AppUser;
import ticketguru.domain.Role;
import ticketguru.repository.AppUserRepository;
import ticketguru.repository.RoleRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class AppUserResource {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private RoleRepository roleRepository;

    private AppUser convertToEntity(AppUserDTO dto) {
        Role role = roleRepository.findById(dto.getRoleId()).orElse(null);
        return new AppUser(dto.getUsername(), dto.getPasswordHash(), role, null, null);
    }

    private AppUserDTO convertToDTO(AppUser user) {
        return new AppUserDTO(
                user.getUserId(),
                user.getUsername(),
                user.getPasswordHash(),
                user.getRole() != null ? user.getRole().getRoleId() : null,
                user.getEvents().stream().map(event -> event.getEventId()).collect(Collectors.toList()),
                user.getSales().stream().map(sale -> sale.getSaleId()).collect(Collectors.toList())
        );
    }

    @GetMapping
    public List<AppUserDTO> getAllUsers() {
        return appUserRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppUserDTO> getUserById(@PathVariable Long id) {
        Optional<AppUser> user = appUserRepository.findById(id);
        return user.map(this::convertToDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AppUserDTO> createUser(@RequestBody AppUserDTO appUserDTO) {
        if (appUserDTO.getUserId() != null) {
            return ResponseEntity.badRequest().build();
        }
        AppUser appUser = convertToEntity(appUserDTO);
        AppUser savedUser = appUserRepository.save(appUser);
        return ResponseEntity.ok(convertToDTO(savedUser));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppUserDTO> updateUser(@PathVariable Long id, @RequestBody AppUserDTO appUserDetails) {
        Optional<AppUser> userOptional = appUserRepository.findById(id);

        if (!userOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        AppUser existingUser = userOptional.get();
        existingUser.setUsername(appUserDetails.getUsername());
        existingUser.setPasswordHash(appUserDetails.getPasswordHash());
        Role role = roleRepository.findById(appUserDetails.getRoleId()).orElse(null);
        existingUser.setRole(role);

        AppUser updatedUser = appUserRepository.save(existingUser);
        return ResponseEntity.ok(convertToDTO(updatedUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (!appUserRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        appUserRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
