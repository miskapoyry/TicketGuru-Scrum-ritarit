package ticketguru.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import ticketguru.DTO.TicketTypeDTO;
import ticketguru.exception.ResourceNotFoundException;
import ticketguru.service.TicketTypeService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ticket-types")
public class TicketTypeResource {

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
        if (ticketType.isEmpty()) {
            throw new ResourceNotFoundException("TicketType with ID " + id + " not found");
        }
        return ResponseEntity.ok(ticketType.get());
    }

    // POST a new ticket type
    @PostMapping(consumes = { "application/json" })
    public ResponseEntity<?> createTicketType(@Valid @RequestBody TicketTypeDTO ticketTypeDTO) {
        if (ticketTypeDTO.getTicketTypeName() == null || ticketTypeDTO.getTicketTypeName().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Ticket type name is required");
        }

        TicketTypeDTO createdTicketType = ticketTypeService.createTicketType(ticketTypeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTicketType);
    }

    // PUT to update a ticket type
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTicketType(@PathVariable Long id, @Valid @RequestBody TicketTypeDTO ticketTypeDTO) {
        if (ticketTypeDTO.getTicketTypeName() == null || ticketTypeDTO.getTicketTypeName().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Ticket type name is required");
        }

        Optional<TicketTypeDTO> updatedTicketType = ticketTypeService.updateTicketType(id, ticketTypeDTO);
        if (updatedTicketType.isEmpty()) {
            throw new ResourceNotFoundException("TicketType with ID " + id + " not found");
        }

        return ResponseEntity.ok(updatedTicketType.get());
    }


    // DELETE a ticket type by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTicketType(@PathVariable Long id) {
        ticketTypeService.deleteTicketType(id);
        return ResponseEntity.noContent().build();
    }

    
}