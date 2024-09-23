package ticketguru.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ticketguru.domain.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
    
}
