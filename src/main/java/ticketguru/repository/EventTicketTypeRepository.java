package ticketguru.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ticketguru.domain.Event;
import ticketguru.domain.EventTicketType;
import ticketguru.domain.TicketType;

import java.util.Optional;

// This interface is a repository for the EventTicketType entity
public interface EventTicketTypeRepository extends JpaRepository<EventTicketType, Long> {

    // Custom query to find an EventTicketType by EventId and TicketTypeId
    Optional<EventTicketType> findByEvent_EventIdAndTicketType_TicketTypeId(Long eventId, Long ticketTypeId);
    Optional<EventTicketType> findByEventAndTicketType(Event event, TicketType ticketType);
}