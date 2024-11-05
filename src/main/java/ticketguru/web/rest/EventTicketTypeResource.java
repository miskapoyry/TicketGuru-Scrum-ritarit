package ticketguru.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ticketguru.DTO.EventTicketTypeDTO;
import ticketguru.service.EventTicketTypeService;
import ticketguru.domain.EventTicketType;
import ticketguru.exception.ResourceNotFoundException;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/eventTicketTypes")
public class EventTicketTypeResource {

    @Autowired
    private EventTicketTypeService eventTicketTypeService;

    // Create a new EventTicketType
    @PostMapping(consumes = { "application/json" }, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createEventTicketTypes(@RequestBody List<EventTicketTypeDTO> eventTicketTypeDTOs) {
        if (eventTicketTypeDTOs == null || eventTicketTypeDTOs.isEmpty()) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "EventTicketTypeDTO list is null or empty.");
            return ResponseEntity.badRequest().body(errorResponse);
        }
        List<EventTicketTypeDTO> createdEventTicketTypes = eventTicketTypeService.createEventTicketTypes(eventTicketTypeDTOs);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEventTicketTypes);
    }

    // Get all EventTicketTypes
    @GetMapping
    public List<EventTicketTypeDTO> getAllEventTicketTypes() {
        return eventTicketTypeService.getAllEventTicketTypes();
    }

    // Get an EventTicketType by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getEventTicketTypeById(@PathVariable Long id) {
        try {
            Optional<EventTicketTypeDTO> eventTicketType = eventTicketTypeService.getEventTicketTypeById(id);
            return eventTicketType.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (ResourceNotFoundException ex) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
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
    public ResponseEntity<?> deleteEventTicketType(@PathVariable Long id) {
        try {
            boolean deleted = eventTicketTypeService.deleteEventTicketType(id);
            if (!deleted) {
                throw new ResourceNotFoundException("EventTicketType with ID " + id + " not found");
            }
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException ex) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        } catch (Exception ex) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "An unexpected error occurred.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}