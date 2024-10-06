package ticketguru.web.rest;

import java.util.List;
import java.util.stream.Collectors;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import ticketguru.DTO.SaleDTO;
import ticketguru.domain.Sale;
import ticketguru.domain.Ticket;
import ticketguru.domain.AppUser;
import ticketguru.repository.AppUserRepository;
import ticketguru.repository.SaleRepository;
import ticketguru.repository.TicketRepository;

@RestController
@RequestMapping("/api/sales")
public class SaleResource {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private TicketRepository ticketRepository;

    // Muuntaa Salesta -> SaleDTOn
    private SaleDTO convertToDTO(Sale sale) {

        List<Long> ticketIds = sale.getTickets().stream()
                .map(Ticket::getTicketId)
                .collect(Collectors.toList());

        return new SaleDTO(
                sale.getSaleId(),
                sale.getPaymentMethod(),
                sale.getTotalPrice(),
                sale.getSaleTimestamp(),
                sale.getAppUser().getUserId(),
                ticketIds);
    }

    @GetMapping
    public List<SaleDTO> getAllSales(@RequestParam(required = false) Long userId) {
        if (userId != null) {
            return saleRepository.findByAppUser_UserId(userId).stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
        } else {
            return saleRepository.findAll().stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
        }
    }

    @GetMapping("/{saleId}")
    public ResponseEntity<SaleDTO> getSale(@PathVariable Long saleId) {
        Sale sale = saleRepository.findById(saleId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sale not found"));

        SaleDTO saleDTO = convertToDTO(sale);
        return new ResponseEntity<>(saleDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SaleDTO> createSale(@RequestBody SaleDTO saleDTO) {

        AppUser appUser = appUserRepository.findById(saleDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with given ID"));

        List<Ticket> tickets = ticketRepository.findAllById(saleDTO.getTicketIds());

        if (tickets.size() != saleDTO.getTicketIds().size()) {
            throw new RuntimeException("Tickets not found with given ID");
        }

        Sale sale = new Sale(appUser, tickets, saleDTO.getSaleTimestamp(),
                saleDTO.getPaymentMethod(), saleDTO.getTotalPrice());

        tickets.forEach(ticket -> ticket.setSale(sale));

        Sale newSale = saleRepository.save(sale);

        return new ResponseEntity<>(convertToDTO(newSale), HttpStatus.CREATED);
    }

    @PutMapping("/{saleId}")
    public ResponseEntity<SaleDTO> updateSale(@PathVariable Long saleId, @RequestBody SaleDTO saleDTO) {
        Sale existingSale = saleRepository.findById(saleId)
                .orElseThrow(() -> new RuntimeException("Sale not found with given ID"));

        List<Ticket> tickets = ticketRepository.findAllById(saleDTO.getTicketIds());

        if (tickets.size() != saleDTO.getTicketIds().size()) {
            throw new RuntimeException("Tickets not found with given ID");
        }

        existingSale.setPaymentMethod(saleDTO.getPaymentMethod());
        existingSale.setTotalPrice(saleDTO.getTotalPrice());
        existingSale.setSaleTimestamp(saleDTO.getSaleTimestamp());

        tickets.forEach(ticket -> ticket.setSale(existingSale));
        existingSale.setTickets(tickets);

        Sale updatedSale = saleRepository.save(existingSale);

        SaleDTO updatedSaleDTO = convertToDTO(updatedSale);
        return new ResponseEntity<>(updatedSaleDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SaleDTO> deleteSale(@PathVariable Long id) {
        if (!saleRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        } else {
            saleRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
    }
}