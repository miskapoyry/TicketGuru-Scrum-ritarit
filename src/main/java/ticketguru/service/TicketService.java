package ticketguru.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ticketguru.DTO.TicketDTO;
import ticketguru.domain.Event;
import ticketguru.domain.EventTicketType;
import ticketguru.domain.Sale;
import ticketguru.domain.Ticket;
import ticketguru.repository.EventRepository;
import ticketguru.repository.EventTicketTypeRepository;
import ticketguru.repository.SaleRepository;
import ticketguru.repository.TicketRepository;
import ticketguru.exception.ResourceNotFoundException; 
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class TicketService {

        @Autowired
        private TicketRepository ticketRepository;

        @Autowired
        private EventRepository eventRepository;

        @Autowired
        private SaleRepository saleRepository;

        @Autowired
        private EventTicketTypeRepository eventTicketTypeRepository;

        public double getTicketPrice(Long eventId, Long ticketTypeId) { // Oma metodinsa hinnan hakemiselle EventTicketTypen perusteella
        EventTicketType eventTicketType = eventTicketTypeRepository
                .findByEvent_EventIdAndTicketType_TicketTypeId(eventId, ticketTypeId) 
                .orElseThrow(() -> new ResourceNotFoundException("EventTicketType not found with given EventId and TicketTypeId"));
        
        return eventTicketType.getPrice(); 
        }

        public List<TicketDTO> getTickets(Long eventId, Long saleId) {
                // Lista lippuja varten
                List<Ticket> tickets;

                // Haetaan lippuja EventId:llä tai SaleId:llä
                if (eventId != null) {
                        Event event = eventRepository.findById(eventId)
                                .orElseThrow(() -> new ResourceNotFoundException("Event not found"));
                        tickets = ticketRepository.findByEvent(event);
                } else if (saleId != null) {
                        Sale sale = saleRepository.findById(saleId)
                                .orElseThrow(() -> new ResourceNotFoundException("Sale not found"));
                        tickets = ticketRepository.findBySale(sale);
                } else {
                        // Tai haetaan kaikki liput
                        tickets = ticketRepository.findAll();
                }

                // Lista TicketDTOita varten
                List<TicketDTO> ticketDTOs = new ArrayList<>();

                // For-loopataan liput DTO-muotoon
                for (Ticket ticket : tickets) {
                        double price = getTicketPrice(ticket.getEvent().getEventId(), ticket.getTicketType().getTicketTypeId());
                        TicketDTO ticketDTO = convertToDTO(ticket, price); 
                        ticketDTOs.add(ticketDTO);
                }

                return ticketDTOs;
        }

        public TicketDTO getTicket(Long ticketId) {
                Ticket ticket = ticketRepository.findById(ticketId)
                        .orElseThrow(() -> new ResourceNotFoundException("Ticket not found"));
                
                // Retrieve the price using the ticket's event and ticket type
                double price = getTicketPrice(ticket.getEvent().getEventId(), ticket.getTicketType().getTicketTypeId());
                
                // convertToDTO:sta otetaan nyt sekä lippu että hinta
                return convertToDTO(ticket, price);
            }

        public TicketDTO markTicketAsUsed(Long ticketId, boolean isUsed) {
                Ticket existingTicket = ticketRepository.findById(ticketId)
                        .orElseThrow(() -> new ResourceNotFoundException("Ticket not found")); 
                // päivitä status
                existingTicket.setUsed(isUsed);
                
                // päivitä käyttöaika, jos lippu käytetään
                if (isUsed) {  // jos käytetään...
                    existingTicket.setUsedTimestamp(new Timestamp(System.currentTimeMillis())); // iske timestamppi käyttöajankohdalle
                } else { // muussa tapauksessa (eli jos päivitetäänkin takaisin käyttämättömäksi)
                    existingTicket.setUsedTimestamp(null); // timestamppi nulliksi
                }
        
                Ticket updatedTicket = ticketRepository.save(existingTicket); 
                double price = getTicketPrice(updatedTicket.getEvent().getEventId(), updatedTicket.getTicketType().getTicketTypeId());
                return convertToDTO(updatedTicket, price);
        }

        public TicketDTO getTicket(String ticketNumber) {
                Ticket ticket = ticketRepository.findByTicketNumber(ticketNumber)
                                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found"));

                // Retrieve the price using the ticket's event and ticket type
                double price = getTicketPrice(ticket.getEvent().getEventId(), ticket.getTicketType().getTicketTypeId());

                // convertToDTO:sta otetaan nyt sekä lippu että hinta
                return convertToDTO(ticket, price);
        }

        public TicketDTO markTicketAsUsed(String ticketNumber, boolean isUsed) {
                Ticket existingTicket = ticketRepository.findByTicketNumber(ticketNumber)
                                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found"));
                // päivitä status
                existingTicket.setUsed(isUsed);

                // päivitä käyttöaika, jos lippu käytetään
                if (isUsed) { // jos käytetään...
                        existingTicket.setUsedTimestamp(new Timestamp(System.currentTimeMillis())); // iske timestamppi
                                                                                                    // käyttöajankohdalle
                } else { // muussa tapauksessa (eli jos päivitetäänkin takaisin käyttämättömäksi)
                        existingTicket.setUsedTimestamp(null); // timestamppi nulliksi
                }

                Ticket updatedTicket = ticketRepository.save(existingTicket);
                double price = getTicketPrice(updatedTicket.getEvent().getEventId(),
                                updatedTicket.getTicketType().getTicketTypeId());
                return convertToDTO(updatedTicket, price);
        }

        private TicketDTO convertToDTO(Ticket ticket, double price) {
                return new TicketDTO(
                                ticket.getTicketId(),
                                ticket.getTicketNumber(),
                                ticket.getEvent().getEventId(),
                                ticket.getTicketType().getTicketTypeId(),
                                ticket.getSale().getSaleId(),
                                ticket.getSaleTimestamp(),
                                ticket.isUsed(),
                                ticket.getUsedTimestamp(),
                                1, price);
        }
}
