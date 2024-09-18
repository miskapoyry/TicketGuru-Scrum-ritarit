package ticketguru.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser,Long> {

    AppUser findByUsername(String username); //Tämä vain täytteenä tulevaisuutta varten
    
}
