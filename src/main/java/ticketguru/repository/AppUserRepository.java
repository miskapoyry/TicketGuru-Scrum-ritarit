package ticketguru.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ticketguru.domain.AppUser;

@Repository// This interface is a repository for the AppUser entity
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    AppUser findByUsername(String username); // Tämä vain täytteenä tulevaisuutta varten
    boolean existsByUsername(String username);
}
