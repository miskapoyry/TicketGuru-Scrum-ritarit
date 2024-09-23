package ticketguru.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ticketguru.domain.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    
}
