package ticketguru.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ticketguru.DTO.TicketDTO;
import ticketguru.domain.Event;
import ticketguru.domain.Sale;
import ticketguru.domain.Ticket;
import ticketguru.repository.EventRepository;
import ticketguru.repository.SaleRepository;
import ticketguru.repository.TicketRepository;
import ticketguru.exception.ResourceNotFoundException; 
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketService {

        @Autowired
        private TicketRepository ticketRepository;

        @Autowired
        private EventRepository eventRepository;

        @Autowired
        private SaleRepository saleRepository;

        public List<TicketDTO> getTickets(Long eventId, Long saleId) {
                // jos EventId on annettu, etsi ko. tapahtumaan kuuluvat liput
                if (eventId != null) {
                    Event event = eventRepository.findById(eventId)
                            .orElseThrow(() -> new ResourceNotFoundException("Event not found")); 
                    return ticketRepository.findByEvent(event).stream()
                            .map(this::convertToDTO)
                            .collect(Collectors.toList());
                }

                // jos SaleId on annettu, etsi ko. tapahtumaan kuuluvat liput
                if (saleId != null) {
                        Sale sale = saleRepository.findById(saleId)
                                .orElseThrow(() -> new ResourceNotFoundException("Sale not found")); 
                        return ticketRepository.findBySale(sale).stream()
                                .map(this::convertToDTO)
                                .collect(Collectors.toList());
                    }
            
                // jos ei ole annettu mitään parametreja, etsi kaikki liput
                return ticketRepository.findAll().stream()
                                .map(this::convertToDTO)
                                .collect(Collectors.toList());
        }

        public TicketDTO getTicket(Long ticketId) {
                return ticketRepository.findById(ticketId)
                        .map(this::convertToDTO)
                        .orElseThrow(() -> new ResourceNotFoundException("Ticket not found"));
        }

        // public TicketDTO createTicket(TicketDTO ticketDTO) {
        //         Event event = eventRepository.findById(ticketDTO.getEventId())
        //                         .orElseThrow(() -> new RuntimeException("Event not found"));

        //         TicketType ticketType = ticketTypeRepository.findById(ticketDTO.getTicketTypeId())
        //                         .orElseThrow(() -> new RuntimeException("Ticket type not found"));

        //         Sale sale = saleRepository.findById(ticketDTO.getSaleId())
        //                         .orElseThrow(() -> new RuntimeException(
        //                                         "Sale not found for ID: " + ticketDTO.getSaleId()));

        //         Ticket ticket = new Ticket(ticketDTO.getTicketNumber(), event, ticketType, sale,
        //                         ticketDTO.getSaleTimestamp(), ticketDTO.isUsed(),
        //                         ticketDTO.getUsedTimestamp());

        //         Ticket newTicket = ticketRepository.save(ticket);

        //         return convertToDTO(newTicket);
        // }

        public TicketDTO markTicketAsUsed(Long ticketId, boolean isUsed) {
                Ticket existingTicket = ticketRepository.findById(ticketId)
                        .orElseThrow(() -> new ResourceNotFoundException("Ticket not found")); 
                // päivitä status
                existingTicket.setUsed(isUsed);
                
                // päivitä käyttöaika, jos lippu käytetään
                if (isUsed) {
                    existingTicket.setUsedTimestamp(new Timestamp(System.currentTimeMillis()));
                } else {
                    existingTicket.setUsedTimestamp(null);
                }
        
                Ticket updatedTicket = ticketRepository.save(existingTicket);
                return convertToDTO(updatedTicket);
        }

        public void deleteTicket(Long ticketId) {
                // tarkista, onko lippu olemassa ennen poistoa
                if (!ticketRepository.existsById(ticketId)) {
                        throw new ResourceNotFoundException("Ticket not found"); 
                }
                ticketRepository.deleteById(ticketId);
        }
        private TicketDTO convertToDTO(Ticket ticket) {
                return new TicketDTO(
                                ticket.getTicketId(),
                                ticket.getTicketNumber(),
                                ticket.getEvent().getEventId(),
                                ticket.getTicketType().getTicketTypeId(),
                                ticket.getSale().getSaleId(),
                                ticket.getSaleTimestamp(),
                                ticket.isUsed(),
                                ticket.getUsedTimestamp(),
                                1, 0);
        }
}
