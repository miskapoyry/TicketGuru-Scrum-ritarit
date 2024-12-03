package ticketguru.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ticketguru.DTO.AppUserDTO;
import ticketguru.domain.AppUser;
import ticketguru.domain.Role;
import ticketguru.repository.AppUserRepository;
import ticketguru.repository.RoleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AppUserService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private AppUser toEntity(AppUserDTO dto) {
        return new AppUser(dto.getUsername(), dto.getPasswordHash(),
                roleRepository.findById(dto.getRoleId()).orElse(null), null, null);
    }

    private AppUserDTO toDTO(AppUser user) {
        return new AppUserDTO(
                user.getUserId(),
                user.getUsername(),
                user.getPasswordHash(),
                Optional.ofNullable(user.getRole()).map(Role::getRoleId).orElse(null),
                user.getEvents().stream().map(event -> event.getEventId()).toList(),
                user.getSales().stream().map(sale -> sale.getSaleId()).toList());
    }

    public List<AppUserDTO> getAllUsers() {
        return appUserRepository.findAll().stream().map(this::toDTO).toList();
    }

    public Optional<AppUserDTO> getUserById(Long id) {
        return appUserRepository.findById(id).map(this::toDTO);
    }

    public AppUserDTO createUser(AppUserDTO dto) {
        if (appUserRepository.existsByUsername(dto.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }
        dto.setPasswordHash(passwordEncoder.encode(dto.getPasswordHash()));
        return toDTO(appUserRepository.save(toEntity(dto)));
    }

    public Optional<AppUserDTO> updateUser(Long id, AppUserDTO dto) {
        return appUserRepository.findById(id).map(user -> {
            user.setUsername(dto.getUsername());
            user.setPasswordHash(passwordEncoder.encode(dto.getPasswordHash()));
            user.setRole(roleRepository.findById(dto.getRoleId()).orElse(null));
            return toDTO(appUserRepository.save(user));
        });
    }

    public boolean deleteUser(Long id) {
        if (!appUserRepository.existsById(id))
            return false;
        appUserRepository.deleteById(id);
        return true;
    }
}
