package ticketguru.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ticketguru.domain.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    
}