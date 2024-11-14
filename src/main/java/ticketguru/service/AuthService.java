package ticketguru.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ticketguru.DTO.LoginDTO;
import ticketguru.domain.AppUser;
import ticketguru.repository.AppUserRepository;

@Service
public class AuthService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean authenticatePassword(LoginDTO loginDTO){
        AppUser user = appUserRepository.findByOptionalUsername(loginDTO.getUsername()).orElse(null);
        if(user == null){
            return false;
        }
        else{
            return passwordEncoder.matches(loginDTO.getPassword(), user.getPasswordHash()); 
        }
    }
    
}
