package ticketguru.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ticketguru.DTO.AppUserDTO;
import ticketguru.service.AppUserService;
import ticketguru.exception.DuplicateResourceException;
import ticketguru.exception.ResourceNotFoundException;

import java.util.List;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class AppUserResource {

    @Autowired
    private AppUserService appUserService;

    @GetMapping
    public List<AppUserDTO> getAllUsers() {
        return appUserService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppUserDTO> getUserById(@PathVariable Long id) {
        AppUserDTO user = appUserService.getUserById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with ID " + id + " not found"));
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<AppUserDTO> createUser(@Valid @RequestBody AppUserDTO appUserDTO) {
        // Checking if the username already exists
        if (appUserService.getAllUsers().stream()
                .anyMatch(user -> user.getUsername().equals(appUserDTO.getUsername()))) {
            throw new DuplicateResourceException("Username already exists: " + appUserDTO.getUsername());
        }

        if (appUserDTO.getUserId() != null) {
            throw new DuplicateResourceException("User ID must not be provided for new users");
        }

        AppUserDTO createdUser = appUserService.createUser(appUserDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppUserDTO> updateUser(@PathVariable Long id, @Valid @RequestBody AppUserDTO appUserDetails) {
        // Checking if the username already exists (excluding own username)
        if (appUserService.getAllUsers().stream()
                .anyMatch(user -> !user.getUserId().equals(id)
                        && user.getUsername().equals(appUserDetails.getUsername()))) {
            throw new DuplicateResourceException("Username already exists: " + appUserDetails.getUsername());
        }

        AppUserDTO updatedUser = appUserService.updateUser(id, appUserDetails)
                .orElseThrow(() -> new ResourceNotFoundException("User with ID " + id + " not found"));
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        boolean deleted = appUserService.deleteUser(id);
        if (!deleted) {
            throw new ResourceNotFoundException("User with ID " + id + " not found");
        }
        return ResponseEntity.noContent().build();
    }
}