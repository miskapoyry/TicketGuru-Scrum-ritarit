package ticketguru.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

import ticketguru.DTO.LoginDTO;
import ticketguru.domain.AppUser;
import ticketguru.repository.AppUserRepository;

@Service
public class AuthService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean authenticatePassword(LoginDTO loginDTO) {
        return Optional.ofNullable(appUserRepository.findByUsername(loginDTO.getUsername()))
                .map(user -> passwordEncoder.matches(loginDTO.getPassword(), user.getPasswordHash()))
                .orElse(false);
    }

    public String getUserRole(String username) {
        return Optional.ofNullable(appUserRepository.findByUsername(username))
                .map(user -> user.getRole().getRoleName())
                .orElse("No role");
    }

    public Long getUserId(String username) {
        return Optional.ofNullable(appUserRepository.findByUsername(username))
                .map(AppUser::getUserId)
                .orElse(null);
    }
}
