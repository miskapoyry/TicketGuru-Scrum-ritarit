package ticketguru.repository;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;

import ticketguru.domain.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    List<Sale> findByAppUser_UserId(Long userId);
}
