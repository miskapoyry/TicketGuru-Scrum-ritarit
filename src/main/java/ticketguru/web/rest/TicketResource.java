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

        @GetMapping
        public List<TicketDTO> getTickets(
                        @RequestParam(value = "eventId", required = false) Long eventId,
                        @RequestParam(value = "saleId", required = false) Long saleId) {
                return ticketService.getTickets(eventId, saleId);
        }

        @GetMapping("/{ticketId}")
        public ResponseEntity<TicketDTO> getTicket(@PathVariable Long ticketId) {
            TicketDTO ticket = ticketService.getTicket(ticketId);
            return ResponseEntity.ok(ticket);
        }
 
        @PutMapping("/{ticketId}/use")
        public ResponseEntity<TicketDTO> markTicketAsUsed(
            @PathVariable Long ticketId,
            @RequestParam(value = "used", defaultValue = "true") boolean used) {
        TicketDTO updatedTicket = ticketService.markTicketAsUsed(ticketId, used);
        return ResponseEntity.ok(updatedTicket); // 200 OK
        }

}