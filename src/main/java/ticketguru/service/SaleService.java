package ticketguru.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ticketguru.DTO.SaleDTO;
import ticketguru.DTO.SaleSummaryDTO;
import ticketguru.DTO.TicketDTO;
import ticketguru.DTO.TicketSummaryDTO;
import ticketguru.domain.Sale;
import ticketguru.domain.Ticket;
import ticketguru.domain.TicketType;
import ticketguru.domain.EventTicketType;
import ticketguru.domain.Event;
import ticketguru.domain.AppUser;
import ticketguru.repository.AppUserRepository;
import ticketguru.repository.SaleRepository;
import ticketguru.repository.TicketRepository;
import ticketguru.repository.EventTicketTypeRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private EventTicketTypeRepository eventTicketTypeRepository;

    public SaleDTO createSale(SaleDTO saleDTO) {
        AppUser appUser = appUserRepository.findById(saleDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with given ID"));

        // Set the sale timestamp to the current local time
        Timestamp saleTimestamp = Timestamp.valueOf(LocalDateTime.now(ZoneId.systemDefault()));

        // Calculate the total price based on the EventTicketType prices
        double totalPrice = saleDTO.getTickets().stream()
                .mapToDouble(ticketDTO -> {
                    EventTicketType eventTicketType = eventTicketTypeRepository
                            .findByEvent_EventIdAndTicketType_TicketTypeId(
                                    ticketDTO.getEventId(), ticketDTO.getTicketTypeId())
                            .orElseThrow(() -> new RuntimeException(
                                    "EventTicketType not found with given EventId and TicketTypeId"));
                    return eventTicketType.getPrice() * ticketDTO.getQuantity();
                })
                .sum();

        BigDecimal roundedTotalPrice = BigDecimal.valueOf(totalPrice).setScale(2, RoundingMode.HALF_UP);
        totalPrice = roundedTotalPrice.doubleValue();

        Sale sale = new Sale(appUser, saleTimestamp, saleDTO.getPaymentMethod(), totalPrice);

        Sale newSale = saleRepository.save(sale);

        // Create tickets from the provided ticket information
        List<Ticket> tickets = new ArrayList<>();
        for (TicketDTO ticketDTO : saleDTO.getTickets()) {
            for (int i = 0; i < ticketDTO.getQuantity(); i++) {
                Ticket ticket = new Ticket();
                ticket.setSale(newSale);
                ticket.setEvent(new Event(ticketDTO.getEventId())); // Assuming Event has a constructor with eventId
                ticket.setTicketType(new TicketType(ticketDTO.getTicketTypeId())); // Assuming TicketType has a
                                                                                   // constructor with ticketTypeId
                ticket.setSaleTimestamp(saleTimestamp); // Use the same sale timestamp for all tickets
                ticket.setUsed(ticketDTO.isUsed());
                ticket.setUsedTimestamp(ticketDTO.getUsedTimestamp());
                tickets.add(ticket);
            }
        }

        ticketRepository.saveAll(tickets);

        newSale.setTickets(tickets);
        saleRepository.save(newSale);

        return convertToDTO(newSale);
    }

    public SaleDTO updateSale(Long saleId, SaleDTO saleDTO) {
        Sale existingSale = saleRepository.findById(saleId)
                .orElseThrow(() -> new RuntimeException("Sale not found with given ID"));

        List<Ticket> tickets = ticketRepository.findAllById(
                saleDTO.getTickets().stream().map(TicketDTO::getTicketId).collect(Collectors.toList()));

        if (tickets.size() != saleDTO.getTickets().size()) {
            throw new RuntimeException("Tickets not found with given ID");
        }

        existingSale.setPaymentMethod(saleDTO.getPaymentMethod());
        existingSale.setTotalPrice(saleDTO.getTotalPrice());
        existingSale.setSaleTimestamp(saleDTO.getSaleTimestamp());

        tickets.forEach(ticket -> {
            TicketDTO ticketDTO = saleDTO.getTickets().stream()
                    .filter(t -> t.getTicketId().equals(ticket.getTicketId()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Ticket not found with given ID"));
            ticket.setTicketNumber(ticketDTO.getTicketNumber());
            ticket.setEvent(new Event(ticketDTO.getEventId())); // Assuming Event has a constructor with eventId
            ticket.setTicketType(new TicketType(ticketDTO.getTicketTypeId())); // Assuming TicketType has a constructor
                                                                               // with ticketTypeId
            ticket.setSaleTimestamp(ticketDTO.getSaleTimestamp());
            ticket.setUsed(ticketDTO.isUsed());
            ticket.setUsedTimestamp(ticketDTO.getUsedTimestamp());
            ticket.setSale(existingSale);
        });

        existingSale.setTickets(tickets);

        Sale updatedSale = saleRepository.save(existingSale);

        return convertToDTO(updatedSale);
    }

    public List<SaleDTO> getSales(List<Long> saleIds) {
        List<Sale> sales = saleRepository.findBySaleIdIn(saleIds);
        return sales.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<SaleDTO> getSalesByUserId(Long userId) {
        List<Sale> sales = saleRepository.findByAppUser_UserId(userId);
        return sales.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<SaleSummaryDTO> getAllSales(Long userId) {
        if (userId != null) {
            return saleRepository.findByAppUser_UserId(userId).stream()
                    .map(this::convertToSummaryDTO)
                    .collect(Collectors.toList());
        } else {
            return saleRepository.findAll().stream()
                    .map(this::convertToSummaryDTO)
                    .collect(Collectors.toList());
        }
    }

    public void deleteSale(Long id) {
        if (!saleRepository.existsById(id)) {
            throw new RuntimeException("Sale not found with given ID");
        }
        saleRepository.deleteById(id);
    }

    private SaleDTO convertToDTO(Sale sale) {
        List<TicketDTO> ticketDTOs = sale.getTickets().stream()
                .map(ticket -> new TicketDTO(
                        ticket.getTicketId(),
                        ticket.getTicketNumber(),
                        ticket.getEvent().getEventId(),
                        ticket.getTicketType().getTicketTypeId(),
                        ticket.getSale().getSaleId(),
                        ticket.getSaleTimestamp(),
                        ticket.isUsed(),
                        ticket.getUsedTimestamp(),
                        1, // Assuming quantity is 1 for each ticket
                        eventTicketTypeRepository.findByEvent_EventIdAndTicketType_TicketTypeId(
                                ticket.getEvent().getEventId(), ticket.getTicketType().getTicketTypeId())
                                .orElseThrow(() -> new RuntimeException(
                                        "EventTicketType not found with given EventId and TicketTypeId"))
                                .getPrice()))
                .collect(Collectors.toList());

        return new SaleDTO(
                sale.getSaleId(),
                sale.getPaymentMethod(),
                sale.getTotalPrice(),
                sale.getSaleTimestamp(),
                sale.getAppUser().getUserId(),
                ticketDTOs);
    }

    private SaleSummaryDTO convertToSummaryDTO(Sale sale) {
        List<TicketSummaryDTO> ticketSummaryDTOs = sale.getTickets().stream()
                .map(ticket -> new TicketSummaryDTO(
                        ticket.getTicketId(),
                        1, // Assuming quantity is 1 for each ticket
                        eventTicketTypeRepository.findByEvent_EventIdAndTicketType_TicketTypeId(
                                ticket.getEvent().getEventId(), ticket.getTicketType().getTicketTypeId())
                                .orElseThrow(() -> new RuntimeException(
                                        "EventTicketType not found with given EventId and TicketTypeId"))
                                .getPrice(),
                        ticket.getEvent().getEventId()))
                .collect(Collectors.toList());

        return new SaleSummaryDTO(
                sale.getSaleId(),
                sale.getPaymentMethod(),
                sale.getTotalPrice(),
                sale.getSaleTimestamp(),
                sale.getAppUser().getUserId(),
                ticketSummaryDTOs);
    }
}