package ticketguru.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ticketguru.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    
}
