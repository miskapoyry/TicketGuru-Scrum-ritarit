package ticketguru.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ticketguru.domain.Event;
import ticketguru.domain.Sale;
import ticketguru.domain.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByEvent(Event event);
    List<Ticket> findBySale(Sale sale);
    List<Ticket> findByEventAndSale(Event event, Sale sale);
    
}