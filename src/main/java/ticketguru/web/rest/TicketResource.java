package ticketguru.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ticketguru.DTO.TicketDTO;
import ticketguru.service.TicketService;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketResource {

    @Autowired
    private TicketService ticketService;

    // Retrieve tickets by optional eventId or saleId filters
    @GetMapping
    public ResponseEntity<List<TicketDTO>> getTickets(
            @RequestParam(value = "eventId", required = false) Long eventId,
            @RequestParam(value = "saleId", required = false) Long saleId) {
        List<TicketDTO> tickets = ticketService.getTickets(eventId, saleId);
        return ResponseEntity.ok(tickets); // 200 OK with list of tickets
    }

    // Retrieve a ticket by its unique ID
    @GetMapping("/id/{ticketId}")
    public ResponseEntity<TicketDTO> getTicketById(@PathVariable Long ticketId) {
        TicketDTO ticket = ticketService.getTicket(ticketId);
        return ResponseEntity.ok(ticket); // 200 OK with ticket details
    }

    // Retrieve a ticket by its ticket number
    @GetMapping("/{ticketNumber}")
    public ResponseEntity<TicketDTO> getTicketByNumber(@PathVariable String ticketNumber) {
        TicketDTO ticket = ticketService.getTicket(ticketNumber);
        return ResponseEntity.ok(ticket); // 200 OK with ticket details
    }

    // Mark a ticket as used or unused by its ID
    @PutMapping("/id/{ticketId}/use")
    public ResponseEntity<TicketDTO> updateTicketUsageById(
            @PathVariable Long ticketId,
            @RequestParam(value = "used", defaultValue = "true") boolean used) {
        TicketDTO updatedTicket = ticketService.markTicketAsUsed(ticketId, used);
        return ResponseEntity.ok(updatedTicket); // 200 OK with updated ticket
    }

    // Mark a ticket as used or unused by its ticket number
    @PutMapping("/{ticketNumber}/use")
    public ResponseEntity<TicketDTO> updateTicketUsageByNumber(
            @PathVariable String ticketNumber,
            @RequestParam(value = "used", defaultValue = "true") boolean used) {
        TicketDTO updatedTicket = ticketService.markTicketAsUsed(ticketNumber, used);
        return ResponseEntity.ok(updatedTicket); // 200 OK with updated ticket
    }
}
