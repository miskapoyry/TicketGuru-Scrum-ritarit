package ticketguru.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ticketguru.domain.EventTicketType;

public interface EventTicketTypeRepository extends JpaRepository<EventTicketType, Long> {
    //LUOTU VARMUUDEN VUOKSI VAIKKA EI VÄLTTÄMÄTTÄ TARVETTA
}
