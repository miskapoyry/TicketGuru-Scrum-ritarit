package ticketguru.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ticketguru.DTO.TicketTypeDTO;
import ticketguru.service.TicketTypeService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ticket-types")
public class TicketTypeController {

    @Autowired
    private TicketTypeService ticketTypeService;

    // GET all ticket types
    @GetMapping
    public List<TicketTypeDTO> getAllTicketTypes() {
        return ticketTypeService.getAllTicketTypes();
    }

    // GET ticket type by ID
    @GetMapping("/{id}")
    public ResponseEntity<TicketTypeDTO> getTicketTypeById(@PathVariable Long id) {
        Optional<TicketTypeDTO> ticketType = ticketTypeService.getTicketTypeById(id);
        return ticketType.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST a new ticket type
    @PostMapping(consumes = { "application/json" })
    public ResponseEntity<TicketTypeDTO> createTicketType(@RequestBody TicketTypeDTO ticketTypeDTO) {
        TicketTypeDTO createdTicketType = ticketTypeService.createTicketType(ticketTypeDTO);
        return ResponseEntity.ok(createdTicketType);
    }

    // DELETE a ticket type by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicketType(@PathVariable Long id) {
        try {
            ticketTypeService.deleteTicketType(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // PATCH to update a ticket type
    @PatchMapping("/{id}")
    public ResponseEntity<TicketTypeDTO> updateTicketType(@PathVariable Long id,
            @RequestBody TicketTypeDTO ticketTypeDTO) {
        Optional<TicketTypeDTO> updatedTicketType = ticketTypeService.updateTicketType(id, ticketTypeDTO);
        return updatedTicketType.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}