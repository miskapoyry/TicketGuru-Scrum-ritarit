package ticketguru.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ticketguru.domain.Event;
import ticketguru.domain.Sale;
import ticketguru.domain.Ticket;
import ticketguru.domain.TicketType;

// This interface is a repository for the Ticket entity
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    // Custom queries to find Tickets by Event, Sale and Event and Sale
    List<Ticket> findByEvent(Event event);

    List<Ticket> findBySale(Sale sale);

    List<Ticket> findByEventAndSale(Event event, Sale sale);

    Optional<Ticket> findByTicketNumber(String ticketNumber);

    int countByEventAndTicketType(Event event, TicketType ticketType);

}