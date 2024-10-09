package ticketguru.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ticketguru.DTO.EventTicketTypeDTO;
import ticketguru.service.EventTicketTypeService;
import ticketguru.domain.EventTicketType;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/eventTicketTypes")
public class EventTicketTypeResource {

    @Autowired
    private EventTicketTypeService eventTicketTypeService;

    // Create a new EventTicketType
    @PostMapping(consumes = { "application/json" })
    public ResponseEntity<List<EventTicketTypeDTO>> createEventTicketTypes(@RequestBody List<EventTicketTypeDTO> eventTicketTypeDTOs) {
        if (eventTicketTypeDTOs == null || eventTicketTypeDTOs.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }
        List<EventTicketTypeDTO> createdEventTicketTypes = eventTicketTypeService.createEventTicketTypes(eventTicketTypeDTOs);
        return ResponseEntity.ok(createdEventTicketTypes);
    }

    // Get all EventTicketTypes
    @GetMapping
    public List<EventTicketTypeDTO> getAllEventTicketTypes() {
        return eventTicketTypeService.getAllEventTicketTypes();
    }

    // Get an EventTicketType by ID
    @GetMapping("/{id}")
    public ResponseEntity<EventTicketTypeDTO> getEventTicketTypeById(@PathVariable Long id) {
        Optional<EventTicketTypeDTO> eventTicketType = eventTicketTypeService.getEventTicketTypeById(id);
        return eventTicketType.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update an EventTicketType by ID
    @PutMapping("/{id}")
    public ResponseEntity<EventTicketTypeDTO> updateEventTicketType(@PathVariable Long id,
            @RequestBody EventTicketType updatedEventTicketType) {
        Optional<EventTicketTypeDTO> updatedEventTicketTypeDTO = eventTicketTypeService.updateEventTicketType(id,
                updatedEventTicketType);
        return updatedEventTicketTypeDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete an EventTicketType by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEventTicketType(@PathVariable Long id) {
        boolean deleted = eventTicketTypeService.deleteEventTicketType(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}