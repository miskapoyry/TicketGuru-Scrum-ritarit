package ticketguru.domain;

import org.springframework.data.repository.CrudRepository;

public interface AppUserRepository extends CrudRepository<AppUser,Long> {

    AppUser findByUsername(String username); //Tämä vain täytteenä tulevaisuutta varten
    
}
