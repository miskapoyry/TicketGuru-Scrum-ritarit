package ticketguru.web.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ticketguru.domain.TicketType;
import ticketguru.DTO.TicketTypeDTO;
import ticketguru.repository.TicketTypeRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/ticket-types")
public class TicketTypeController {

    private final TicketTypeRepository ticketTypeRepository;

    public TicketTypeController(TicketTypeRepository ticketTypeRepository) {
        this.ticketTypeRepository = ticketTypeRepository;
    }

    // GET all ticket types
    @GetMapping
    public List<TicketTypeDTO> getAllTicketTypes() {
        List<TicketType> ticketTypes = ticketTypeRepository.findAll();
        return ticketTypes.stream()
                          .map(this::toDTO)
                          .collect(Collectors.toList());
    }

    // GET ticket type by ID
    @GetMapping("/{id}")
    public ResponseEntity<TicketTypeDTO> getTicketTypeById(@PathVariable Long id) {
        return ticketTypeRepository.findById(id)
                .map(ticketType -> ResponseEntity.ok(toDTO(ticketType)))
                .orElse(ResponseEntity.notFound().build());
    }

    // POST a new ticket type
    @PostMapping(consumes = {"application/json"})
    public ResponseEntity<TicketTypeDTO> createTicketType(@RequestBody TicketTypeDTO ticketTypeDTO) {
        TicketType ticketType = new TicketType(ticketTypeDTO.getTicketTypeName());
        TicketType savedTicketType = ticketTypeRepository.save(ticketType);
        return ResponseEntity.ok(toDTO(savedTicketType));
    }

    // DELETE a ticket type by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicketType(@PathVariable Long id) {
        if (!ticketTypeRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        ticketTypeRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // PATCH to update a ticket type
    @PatchMapping("/{id}")
    public ResponseEntity<TicketTypeDTO> updateTicketType(@PathVariable Long id, @RequestBody TicketTypeDTO ticketTypeDTO) {
        return ticketTypeRepository.findById(id)
                .map(existingTicketType -> {
                    existingTicketType.setTicketTypeName(ticketTypeDTO.getTicketTypeName());
                    TicketType updatedTicketType = ticketTypeRepository.save(existingTicketType);
                    return ResponseEntity.ok(toDTO(updatedTicketType));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Convert TicketType to TicketTypeDTO
    private TicketTypeDTO toDTO(TicketType ticketType) {
        return new TicketTypeDTO(
                ticketType.getTicketTypeId(),
                ticketType.getTicketTypeName()
        );
    }
}
