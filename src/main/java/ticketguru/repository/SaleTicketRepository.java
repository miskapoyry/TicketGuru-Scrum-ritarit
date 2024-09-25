package ticketguru.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ticketguru.domain.SaleTicket;

public interface SaleTicketRepository extends JpaRepository<SaleTicket, Long> {
    //LUOTU VARMUUDEN VUOKSI VAIKKA EI VÄLTTÄMÄTTÄ TARVETTA
}
