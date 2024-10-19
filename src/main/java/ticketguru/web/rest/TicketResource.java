package ticketguru.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ticketguru.DTO.TicketDTO;
import ticketguru.service.TicketService;
import ticketguru.exception.ErrorResponse;
import ticketguru.exception.ResourceNotFoundException;

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
        public ResponseEntity<?> getTicket(@PathVariable Long ticketId) {
                try {
                        TicketDTO ticket = ticketService.getTicket(ticketId);
                        return ResponseEntity.ok(ticket);
                } catch (ResourceNotFoundException e) {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(e.getMessage()));
                }
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
    public ResponseEntity<?> markTicketAsUsed(
            @PathVariable Long ticketId,
            @RequestParam(value = "used", defaultValue = "true") boolean used) {
        try {
            TicketDTO updatedTicket = ticketService.markTicketAsUsed(ticketId, used);
            return ResponseEntity.ok(updatedTicket); // 200 OK
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(e.getMessage()));
        }
        }


        @DeleteMapping("/{ticketId}")
        public ResponseEntity<?> deleteTicket(@PathVariable Long ticketId) {
        try {
            ticketService.deleteTicket(ticketId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(e.getMessage()));
        }
    }
}