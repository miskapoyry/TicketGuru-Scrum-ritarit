package ticketguru.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ticketguru.DTO.LoginDTO;
import ticketguru.service.AuthService;

public class AuthResource {

    @Autowired
    private AuthService authService;

    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO){
        boolean authenticated = authService.authenticatePassword(loginDTO);
        if(authenticated){
            return ResponseEntity.ok("Login successful");
        }
        else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Username or password is wrong");
        }
    }
}
