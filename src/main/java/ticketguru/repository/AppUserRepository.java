package ticketguru.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ticketguru.domain.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser,Long> {

    AppUser findByUsername(String username); //Tämä vain täytteenä tulevaisuutta varten
    
}
