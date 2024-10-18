package ticketguru.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ticketguru.domain.Event;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByEventNameContainsIgnoreCase(String eventName);
    List<Event> findByLocationContainsIgnoreCase(String location);
    List<Event> findByEventNameContainsIgnoreCaseAndLocationContainsIgnoreCase(String eventName, String location);
}