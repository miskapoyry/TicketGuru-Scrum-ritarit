package ticketguru.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import ticketguru.DTO.SaleDTO;
import ticketguru.DTO.SalesSummaryDTO;
import ticketguru.DTO.TicketDTO;
import ticketguru.domain.Sale;
import ticketguru.domain.Ticket;
import ticketguru.domain.TicketType;
import ticketguru.exception.InvalidInputException;
import ticketguru.exception.ResourceNotFoundException;
import ticketguru.domain.EventTicketType;
import ticketguru.domain.PaymentMethod;
import ticketguru.domain.Event;
import ticketguru.domain.AppUser;
import ticketguru.repository.AppUserRepository;
import ticketguru.repository.SaleRepository;
import ticketguru.repository.TicketRepository;
import ticketguru.repository.EventTicketTypeRepository;
import ticketguru.repository.PaymentMethodRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.Map;

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

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @Autowired
    private ticketguru.repository.EventRepository eventRepository;

    @Transactional
    public SaleDTO createSale(SaleDTO saleDTO) {
        // Lisää salen luoma käyttäjä
        AppUser appUser = appUserRepository.findById(saleDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with given ID"));
    
        // Tee localdatesta timestamp
        Timestamp saleTimestamp = Timestamp.valueOf(LocalDateTime.now(ZoneId.systemDefault()));

        // Hae maksutapa
        PaymentMethod paymentMethod = paymentMethodRepository.findById(saleDTO.getPaymentMethodId())
                .orElseThrow(() -> new ResourceNotFoundException("Payment method not found with given ID"));

        // Hae tietyn eventin lipputyypit ja niiden hinnat + pyöristä
        double totalPrice = saleDTO.getTickets().stream()
                .mapToDouble(ticketDTO -> {
                    EventTicketType eventTicketType = eventTicketTypeRepository
                            .findByEvent_EventIdAndTicketType_TicketTypeId(
                                    ticketDTO.getEventId(), ticketDTO.getTicketTypeId())
                            .orElseThrow(() -> new ResourceNotFoundException(
                                    "EventTicketType not found with given EventId and TicketTypeId"));
                    return eventTicketType.getPrice() * ticketDTO.getQuantity();
                })
                .sum();
    
        BigDecimal roundedTotalPrice = BigDecimal.valueOf(totalPrice).setScale(2, RoundingMode.HALF_UP);
        totalPrice = roundedTotalPrice.doubleValue();

        // Luo uusi sale jossa on käyttäjä, aika, maksutyyppi sekä lippujen
        // kokonaishinta
        Sale sale = new Sale(appUser, saleTimestamp, paymentMethod, totalPrice);

        // Tallenna luotu sale
        Sale newSale = saleRepository.save(sale);
    
        // Luo liput annetun kutsun perusteella
        List<Ticket> tickets = new ArrayList<>();
        for (TicketDTO ticketDTO : saleDTO.getTickets()) {  // Käy läpi jokainen lippuDTO 
            Event event = eventRepository.findById(ticketDTO.getEventId()) // Hae tapahtuma ID:n perusteella ticketDTO:sta
                    .orElseThrow(() -> new ResourceNotFoundException("Event not found with given ID")); // Heitä poikkeus, jos tapahtumaa ei löydy
    
            int newAvailableTickets = event.getAvailableTickets() - ticketDTO.getQuantity(); // Laske uusi saatavilla olevien lippujen määrä 
            if (newAvailableTickets < 0) {
                throw new InvalidInputException("Not enough available tickets for event: " + event.getEventName());
            } // Heitä poikkeus, jos saatavilla olevien lippujen määrä on negatiivinen
            event.setAvailableTickets(newAvailableTickets); //  Aseta uusi saatavilla olevien lippujen määrä
            eventRepository.save(event); // Tallenna tapahtuma
    
            for (int i = 0; i < ticketDTO.getQuantity(); i++) { // Luodaan quantityn mukainen määrä uusia lippuDTO
                Ticket ticket = new Ticket();
                ticket.setSale(newSale);
                ticket.setEvent(event); // Määritellään tapahtuma, johon lippu(t) luodaan
                ticket.setTicketType(new TicketType(ticketDTO.getTicketTypeId())); // Asetetaan ID:n avulla lipputyyppi
                ticket.setSaleTimestamp(saleTimestamp); // Käytä jokaiselle lipulle samaa luontiaikaa
                ticket.setUsed(ticketDTO.isUsed()); // Määritä onko lippu käytetty vai ei
                ticket.setUsedTimestamp(ticketDTO.getUsedTimestamp()); // Käytetyn lipun aika
                tickets.add(ticket);
            }
        }
    
        // Tallenna liput
        ticketRepository.saveAll(tickets);
    
        // Liitä luodut liput saleen ja tallenna sale
        newSale.setTickets(tickets);
        saleRepository.save(newSale);
    
        return convertToDTO(newSale);
    }

    public SaleDTO getSaleById(Long saleId) {
        // Hae myynti ID:n perusteella ja heitä ResourceNotFoundException, jos myyntiä
        // ei löydy
        Sale sale = saleRepository.findById(saleId)
                .orElseThrow(() -> new ResourceNotFoundException("Sale not found with given ID: " + saleId));
        return convertToDTO(sale);
    }

    // SIIRRETÄÄN TIEDOT TICKETSERVICEEn

    /*
     * public SaleDTO updateSale(Long saleId, SaleDTO saleDTO) {
     * Sale existingSale = saleRepository.findById(saleId)
     * .orElseThrow(() -> new
     * ResourceNotFoundException("Sale not found with given ID"));
     *
     * AppUser appUser = appUserRepository.findById(saleDTO.getUserId())
     * .orElseThrow(() -> new
     * ResourceNotFoundException("User not found with given ID"));
     *
     * List<Ticket> tickets = ticketRepository.findAllById(
     * saleDTO.getTickets().stream().map(TicketDTO::getTicketId).collect(Collectors.
     * toList()));
     *
     * if (tickets.size() != saleDTO.getTickets().size()) {
     * throw new ResourceNotFoundException("Tickets not found with given ID");
     * }
     *
     * existingSale.setPaymentMethod(saleDTO.getPaymentMethod());
     * existingSale.setTotalPrice(saleDTO.getTotalPrice());
     * existingSale.setSaleTimestamp(saleDTO.getSaleTimestamp());
     *
     * existingSale.setAppUser(appUser);
     *
     * tickets.forEach(ticket -> {
     * TicketDTO ticketDTO = saleDTO.getTickets().stream()
     * .filter(t -> t.getTicketId().equals(ticket.getTicketId()))
     * .findFirst()
     * .orElseThrow(() -> new
     * ResourceNotFoundException("Ticket not found with given ID"));
     * ticket.setTicketNumber(ticketDTO.getTicketNumber());
     * ticket.setEvent(new Event(ticketDTO.getEventId())); // Assuming Event has a
     * constructor with eventId
     * ticket.setTicketType(new TicketType(ticketDTO.getTicketTypeId())); //
     * Assuming TicketType has a constructor
     * // with ticketTypeId
     * ticket.setSaleTimestamp(ticketDTO.getSaleTimestamp());
     * ticket.setUsed(ticketDTO.isUsed());
     * ticket.setUsedTimestamp(ticketDTO.getUsedTimestamp());
     * ticket.setSale(existingSale);
     * });
     *
     * existingSale.setTickets(tickets);
     *
     * Sale updatedSale = saleRepository.save(existingSale);
     *
     * return convertToDTO(updatedSale);
     * }
     */

    public SaleDTO updateSale(Long saleId, SaleDTO saleDTO) {
        Sale existingSale = saleRepository.findById(saleId)
                .orElseThrow(() -> new ResourceNotFoundException("Sale not found with given ID"));

        AppUser appUser = appUserRepository.findById(saleDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with given ID"));

        PaymentMethod paymentMethod = paymentMethodRepository.findById(saleDTO.getPaymentMethodId())
                .orElseThrow(() -> new ResourceNotFoundException("Payment method not found with given ID"));

        // Tee localdatesta timestamp
        Timestamp saleTimestamp = Timestamp.valueOf(LocalDateTime.now(ZoneId.systemDefault()));

        // Hae tietyn eventin lipputyypit ja niiden hinnat + pyöristä
        double totalPrice = saleDTO.getTickets().stream()
                .mapToDouble(ticketDTO -> {
                    EventTicketType eventTicketType = eventTicketTypeRepository
                            .findByEvent_EventIdAndTicketType_TicketTypeId(
                                    ticketDTO.getEventId(), ticketDTO.getTicketTypeId())
                            .orElseThrow(() -> new ResourceNotFoundException(
                                    "EventTicketType not found with given EventId and TicketTypeId"));
                    return eventTicketType.getPrice() * ticketDTO.getQuantity();
                })
                .sum();

        BigDecimal roundedTotalPrice = BigDecimal.valueOf(totalPrice).setScale(2, RoundingMode.HALF_UP);
        totalPrice = roundedTotalPrice.doubleValue();

        // Laita päivityt tiedot
        existingSale.setPaymentMethod(paymentMethod);
        existingSale.setTotalPrice(totalPrice);
        existingSale.setSaleTimestamp(saleTimestamp);
        existingSale.setAppUser(appUser);

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

    public List<SaleDTO> getAllSales(Long userId) {
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
    public SalesSummaryDTO generateSalesSummaryReport(Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with given ID"));

        List<Sale> sales = saleRepository.findAll().stream()
                .filter(sale -> sale.getTickets().stream().anyMatch(ticket -> ticket.getEvent().equals(event)))
                .collect(Collectors.toList());

        int totalSales = sales.size();
        double totalRevenue = sales.stream().mapToDouble(Sale::getTotalPrice).sum();

        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        Map<String, Integer> salesByWeek = sales.stream()
                .collect(Collectors.groupingBy(sale -> {
                    LocalDate saleDate = sale.getSaleTimestamp().toLocalDateTime().toLocalDate();
                    int weekOfYear = saleDate.get(weekFields.weekOfWeekBasedYear());
                    int year = saleDate.getYear();
                    return year + "-W" + weekOfYear;
                }, Collectors.summingInt(sale -> 1)));

        Map<String, Double> revenueByWeek = sales.stream()
                .collect(Collectors.groupingBy(sale -> {
                    LocalDate saleDate = sale.getSaleTimestamp().toLocalDateTime().toLocalDate();
                    int weekOfYear = saleDate.get(weekFields.weekOfWeekBasedYear());
                    int year = saleDate.getYear();
                    return year + "-W" + weekOfYear;
                }, Collectors.summingDouble(Sale::getTotalPrice)));

        Map<String, Integer> salesByUser = sales.stream()
                .collect(Collectors.groupingBy(sale -> sale.getAppUser().getUsername(), Collectors.summingInt(sale -> 1)));

        return new SalesSummaryDTO(totalSales, totalRevenue, salesByWeek, revenueByWeek, salesByUser);
    }

    public void deleteSale(Long id) {
        if (!saleRepository.existsById(id)) {
            throw new ResourceNotFoundException("Sale not found with given ID");
        }
        saleRepository.deleteById(id);
    }

    private SaleDTO convertToDTO(Sale sale) {
        List<TicketDTO> ticketDTOs = sale.getTickets().stream()
                .map(ticket -> new TicketDTO(
                        ticket.getTicketId(),
                        ticket.getTicketNumber(),
                        ticket.getEvent().getEventId(),
                        ticket.getEvent().getEventName(),
                        ticket.getTicketType().getTicketTypeId(),
                        ticket.getTicketType().getTicketTypeName(),
                        ticket.getSale().getSaleId(),
                        ticket.getSaleTimestamp(),
                        ticket.isUsed(),
                        ticket.getUsedTimestamp(),
                        1, // Assuming quantity is 1 for each ticket
                        eventTicketTypeRepository.findByEvent_EventIdAndTicketType_TicketTypeId(
                                        ticket.getEvent().getEventId(), ticket.getTicketType().getTicketTypeId())
                                .orElseThrow(() -> new ResourceNotFoundException(
                                        "EventTicketType not found with given EventId and TicketTypeId"))
                                .getPrice()))
                .collect(Collectors.toList());

        return new SaleDTO(
                sale.getSaleId(),
                sale.getPaymentMethod().getPaymentMethodId(),
                sale.getTotalPrice(),
                sale.getSaleTimestamp(),
                sale.getAppUser().getUserId(),
                ticketDTOs);
    }
}