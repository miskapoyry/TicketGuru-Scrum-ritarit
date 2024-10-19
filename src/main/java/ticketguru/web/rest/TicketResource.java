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

        // @PostMapping
        // public ResponseEntity<TicketDTO> createTicket(@RequestBody TicketDTO ticketDTO) {
        //         try {
        //                 TicketDTO createdTicket = ticketService.createTicket(ticketDTO);
        //                 return new ResponseEntity<>(createdTicket, HttpStatus.CREATED);
        //         } catch (RuntimeException e) {
        //                 return ResponseEntity.badRequest().body(null);
        //         }
        // }
        
        @PutMapping("/{ticketId}/use")
        public ResponseEntity<TicketDTO> markTicketAsUsed(
                @PathVariable Long ticketId,
                @RequestParam(value = "used", defaultValue = "true") boolean used) {  // Defaulttina kutsussa lippu käytetty
            try {
                TicketDTO updatedTicket = ticketService.markTicketAsUsed(ticketId, used);
                return ResponseEntity.ok(updatedTicket);
            } catch (RuntimeException e) {
                return ResponseEntity.notFound().build();
            }
        }

        @DeleteMapping("/{ticketId}")
        public ResponseEntity<Void> deleteTicket(@PathVariable Long ticketId) {
            try {
                ticketService.deleteTicket(ticketId);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No content, jos onnistuu
            } catch (RuntimeException e) {
                return ResponseEntity.notFound().build(); // Jos lippua ei löydy
            }
        }
}