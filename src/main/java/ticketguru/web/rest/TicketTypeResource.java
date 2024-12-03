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

@RestController
@RequestMapping("/api/ticket-types")
public class TicketTypeResource {

    @Autowired
    private TicketTypeService ticketTypeService;

    // GET all ticket types
    @GetMapping
    public ResponseEntity<List<TicketTypeDTO>> getAllTicketTypes() {
        List<TicketTypeDTO> ticketTypes = ticketTypeService.getAllTicketTypes();
        return ResponseEntity.ok(ticketTypes); // Return 200 OK with the list of ticket types
    }

    // GET ticket type by ID
    @GetMapping("/{id}")
    public ResponseEntity<TicketTypeDTO> getTicketTypeById(@PathVariable Long id) {
        return ticketTypeService.getTicketTypeById(id)
                .map(ResponseEntity::ok) // Return 200 OK if the ticket type is found
                .orElseThrow(() -> new ResourceNotFoundException("TicketType with ID " + id + " not found"));
    }

    // POST a new ticket type
    @PostMapping(consumes = "application/json")
    public ResponseEntity<TicketTypeDTO> createTicketType(@Valid @RequestBody TicketTypeDTO ticketTypeDTO) {
        TicketTypeDTO createdTicketType = ticketTypeService.createTicketType(ticketTypeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTicketType); // Return 201 Created with the created ticket type
    }

    // PUT to update a ticket type
    @PutMapping("/{id}")
    public ResponseEntity<TicketTypeDTO> updateTicketType(
            @PathVariable Long id, @Valid @RequestBody TicketTypeDTO ticketTypeDTO) {
        return ticketTypeService.updateTicketType(id, ticketTypeDTO)
                .map(ResponseEntity::ok) // Return 200 OK with the updated ticket type
                .orElseThrow(() -> new ResourceNotFoundException("TicketType with ID " + id + " not found"));
    }

    // DELETE a ticket type by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicketType(@PathVariable Long id) {
        ticketTypeService.deleteTicketType(id); // Delegate deletion to the service
        return ResponseEntity.noContent().build(); // Return 204 No Content
    }
}
