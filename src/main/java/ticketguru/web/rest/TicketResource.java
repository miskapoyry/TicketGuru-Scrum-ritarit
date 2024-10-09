package ticketguru.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ticketguru.DTO.TicketDTO;
import ticketguru.service.TicketService;

import java.util.List;
import java.util.Optional;

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
                Optional<TicketDTO> ticket = ticketService.getTicket(ticketId);
                return ticket.map(ResponseEntity::ok)
                                .orElseGet(() -> ResponseEntity.notFound().build());
        }

        @PostMapping
        public ResponseEntity<TicketDTO> createTicket(@RequestBody TicketDTO ticketDTO) {
                try {
                        TicketDTO createdTicket = ticketService.createTicket(ticketDTO);
                        return new ResponseEntity<>(createdTicket, HttpStatus.CREATED);
                } catch (RuntimeException e) {
                        return ResponseEntity.badRequest().body(null);
                }
        }

        @PutMapping("/{ticketId}")
        public ResponseEntity<TicketDTO> updateTicket(@PathVariable Long ticketId, @RequestBody TicketDTO ticketDTO) {
                try {
                        TicketDTO updatedTicket = ticketService.updateTicket(ticketId, ticketDTO);
                        return ResponseEntity.ok(updatedTicket);
                } catch (RuntimeException e) {
                        return ResponseEntity.notFound().build();
                }
        }

        @DeleteMapping("/{ticketId}")
        public ResponseEntity<Void> deleteTicket(@PathVariable Long ticketId) {
                ticketService.deleteTicket(ticketId);
                return ResponseEntity.ok().build();
        }
}