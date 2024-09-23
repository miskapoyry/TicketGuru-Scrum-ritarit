package ticketguru.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ticketguru.domain.TicketType;

public interface TicketTypeRepository extends JpaRepository<TicketType, Long> {

    
}