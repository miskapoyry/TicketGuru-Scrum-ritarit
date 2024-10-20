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
        return ticketTypeService.getAllTicketTypes(); // Haetaan Servicestä kaikki lipputyypit
    }

    // GET ticket type by ID
    @GetMapping("/{id}")
    public ResponseEntity<TicketTypeDTO> getTicketTypeById(@PathVariable Long id) {
        Optional<TicketTypeDTO> ticketType = ticketTypeService.getTicketTypeById(id); // Haetaan lipputyyppi ID:llä
        if (ticketType.isEmpty()) {
            throw new ResourceNotFoundException("TicketType with ID " + id + " not found"); // Jos ei löydy, tämä virhe
        }
        return ResponseEntity.ok(ticketType.get()); // Jos löytyy, 200 OK
    }

    // POST a new ticket type
    @PostMapping(consumes = { "application/json" })
    public ResponseEntity<?> createTicketType(@Valid @RequestBody TicketTypeDTO ticketTypeDTO) {
        TicketTypeDTO createdTicketType = ticketTypeService.createTicketType(ticketTypeDTO); // Luodaan uusi lippu pyynnön mukaisesti
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTicketType); // Palautetaan lippu, 201 Created
    }

    // PUT to update a ticket type
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTicketType(@PathVariable Long id, @Valid @RequestBody TicketTypeDTO ticketTypeDTO) {
        Optional<TicketTypeDTO> updatedTicketType = ticketTypeService.updateTicketType(id, ticketTypeDTO); // Yritys päivittää lippu
        if (updatedTicketType.isEmpty()) { 
            throw new ResourceNotFoundException("TicketType with ID " + id + " not found");  // jos ei löydy, virhe
        }
        return ResponseEntity.ok(updatedTicketType.get()); // jos löytyy, 200 OK
    }


    // DELETE a ticket type by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTicketType(@PathVariable Long id) {
        ticketTypeService.deleteTicketType(id); // Kutsutaan Servicessä olevaa metodia tekemään poisto
        return ResponseEntity.noContent().build(); // 204 No Content, jos onnistuu
    }

    
}