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
import java.util.stream.Collectors;

@Service
public class AppUserService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
                user.getSales().stream().map(sale -> sale.getSaleId()).collect(Collectors.toList()));
    }

    public List<AppUserDTO> getAllUsers() {
        return appUserRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<AppUserDTO> getUserById(Long id) {
        return appUserRepository.findById(id).map(this::convertToDTO);
    }

    public AppUserDTO createUser(AppUserDTO appUserDTO) {
        if (appUserRepository.existsByUsername(appUserDTO.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }
    
        AppUser appUser = convertToEntity(appUserDTO);
        appUser.setPasswordHash(passwordEncoder.encode(appUserDTO.getPasswordHash()));
        AppUser savedUser = appUserRepository.save(appUser);
        return convertToDTO(savedUser);
    }

    public Optional<AppUserDTO> updateUser(Long id, AppUserDTO appUserDetails) {

        if (!appUserRepository.existsById(id)) {
            return Optional.empty();
        }

        AppUser existingUser = appUserRepository.findById(id).get();
        existingUser.setUsername(appUserDetails.getUsername());
        existingUser.setPasswordHash(appUserDetails.getPasswordHash());
        Role role = roleRepository.findById(appUserDetails.getRoleId()).orElse(null);
        existingUser.setRole(role);

        AppUser updatedUser = appUserRepository.save(existingUser);
        return Optional.of(convertToDTO(updatedUser));
    }

    public boolean deleteUser(Long id) {
        if (!appUserRepository.existsById(id)) {
            return false;
        }
        appUserRepository.deleteById(id);
        return true;
    }
}
