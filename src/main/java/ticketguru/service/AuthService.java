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
        AppUser user = appUserRepository.findByUsername(loginDTO.getUsername());
        if(user == null){
            return false;
        }
        else{
            return passwordEncoder.matches(loginDTO.getPassword(), user.getPasswordHash()); 
        }
    }
    
    public String getUserRole(String username){
        AppUser user = appUserRepository.findByUsername(username);
        if(user != null){
            return user.getRole().getRoleName();
        }
        return "No role";
    }
    public Long getUserId(String username){
        AppUser user = appUserRepository.findByUsername(username);
        if(user != null){
            return user.getUserId();
        }
        return null;
    }
}
