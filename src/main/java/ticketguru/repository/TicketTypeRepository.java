package ticketguru.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import ticketguru.domain.TicketType;

public interface TicketTypeRepository extends JpaRepository<TicketType, Long> {

    boolean existsByTicketTypeName(String ticketTypeName);
    Optional<TicketType> findByTicketTypeName(String ticketTypeName);
}