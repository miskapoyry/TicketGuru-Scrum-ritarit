package ticketguru.web.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ticketguru.DTO.TicketDTO;
import ticketguru.domain.Event; 
import ticketguru.domain.Sale;
import ticketguru.domain.Ticket;
import ticketguru.domain.TicketType;
import ticketguru.repository.EventRepository;
import ticketguru.repository.SaleRepository;
import ticketguru.repository.TicketRepository;
import ticketguru.repository.TicketTypeRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/tickets")
public class TicketResource {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private TicketTypeRepository ticketTypeRepository;

    @Autowired
    private SaleRepository saleRepository;
    
    private TicketDTO convertToDTO(Ticket ticket) {
        
        return new TicketDTO(
            ticket.getTicketId(),
            ticket.getTicketNumber(),
            ticket.getEvent().getEventId(),
            ticket.getTicketType().getTicketTypeId(),
            ticket.getSale().getSaleId(),
            ticket.getSaleTimestamp(),
            ticket.isUsed(),
            ticket.getUsedTimestamp()

        );
    }

 
    @GetMapping
    public List<TicketDTO> getTickets(
        @RequestParam(value = "eventId", required = false) Long eventId,
        @RequestParam(value = "saleId", required = false) Long saleId){

        if (eventId != null) {
                Event event = eventRepository.findById(eventId)
                                .orElseThrow(() -> new RuntimeException("Event not found"));
                return ticketRepository.findByEvent(event).stream()
                                       .map(this::convertToDTO)
                                       .collect(Collectors.toList());
        }

        if (saleId != null) {
                Sale sale = saleRepository.findById(saleId)
                                .orElseThrow(() -> new RuntimeException("Sale not found"));
                return ticketRepository.findBySale(sale).stream()
                                       .map(this::convertToDTO)
                                       .collect(Collectors.toList());
        }


        return ticketRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

   
    @GetMapping("/{ticketId}")
    public ResponseEntity<TicketDTO> getTicket(@PathVariable Long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));
    
        return new ResponseEntity<>(convertToDTO(ticket), HttpStatus.OK);
    }
   

    @PostMapping
     public ResponseEntity<TicketDTO> createTicket(@RequestBody TicketDTO ticketDTO) {

        Event event = eventRepository.findById(ticketDTO.getEventId())
                .orElseThrow(() -> new RuntimeException("Event not found"));

        TicketType ticketType = ticketTypeRepository.findById(ticketDTO.getTicketTypeId())
                .orElseThrow(() -> new RuntimeException("Ticket type not found"));

        Sale sale = saleRepository.findById(ticketDTO.getSaleId())
                .orElseThrow(() -> new RuntimeException("Sale not found for ID: " + ticketDTO.getSaleId()));
        System.out.println("Found Sale: " + sale);

        Ticket ticket = new Ticket(ticketDTO.getTicketNumber(), event, ticketType, sale, 
                                   ticketDTO.getSaleTimestamp(), ticketDTO.isUsed(), 
                                   ticketDTO.getUsedTimestamp());

        Ticket newTicket = ticketRepository.save(ticket);

        return new ResponseEntity<>(convertToDTO(newTicket), HttpStatus.CREATED);
    }
    
    @PutMapping("/{ticketId}")
    public ResponseEntity<TicketDTO> updateTicket(@PathVariable Long ticketId, @RequestBody TicketDTO ticketDTO) {
        Ticket existingTicket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        Event event = eventRepository.findById(ticketDTO.getEventId())
                .orElseThrow(() -> new RuntimeException("Event not found"));

        TicketType ticketType = ticketTypeRepository.findById(ticketDTO.getTicketTypeId())
                .orElseThrow(() -> new RuntimeException("Ticket type not found"));

        Sale sale = saleRepository.findById(ticketDTO.getSaleId())
                .orElseThrow(() -> new RuntimeException("Sale not found"));

        existingTicket.setTicketNumber(ticketDTO.getTicketNumber());
        existingTicket.setEvent(event);
        existingTicket.setTicketType(ticketType);
        existingTicket.setSale(sale);
        existingTicket.setSaleTimestamp(ticketDTO.getSaleTimestamp());
        existingTicket.setUsed(ticketDTO.isUsed());
        existingTicket.setUsedTimestamp(ticketDTO.getUsedTimestamp());

        Ticket updatedTicket = ticketRepository.save(existingTicket);

        return new ResponseEntity<>(convertToDTO(updatedTicket), HttpStatus.OK);
    }

    @DeleteMapping("/{ticketId}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long ticketId) {
        ticketRepository.deleteById(ticketId);
        return ResponseEntity.ok().build();
    }
    

}
