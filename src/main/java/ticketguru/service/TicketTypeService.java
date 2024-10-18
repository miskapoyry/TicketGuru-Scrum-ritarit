package ticketguru.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ticketguru.DTO.TicketTypeDTO;
import ticketguru.domain.TicketType;
import ticketguru.repository.TicketTypeRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TicketTypeService {

    @Autowired
    private TicketTypeRepository ticketTypeRepository;

    public List<TicketTypeDTO> getAllTicketTypes() {
        List<TicketType> ticketTypes = ticketTypeRepository.findAll();
        return ticketTypes.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<TicketTypeDTO> getTicketTypeById(Long id) {
        return ticketTypeRepository.findById(id).map(this::toDTO);
    }

    public TicketTypeDTO createTicketType(TicketTypeDTO ticketTypeDTO) {
        TicketType ticketType = new TicketType(ticketTypeDTO.getTicketTypeName());
        TicketType savedTicketType = ticketTypeRepository.save(ticketType);
        return toDTO(savedTicketType);
    }

    public Optional<TicketTypeDTO> updateTicketType(Long id, TicketTypeDTO ticketTypeDTO) {
        return ticketTypeRepository.findById(id)
                .map(existingTicketType -> {
                    existingTicketType.setTicketTypeName(ticketTypeDTO.getTicketTypeName());
                    TicketType updatedTicketType = ticketTypeRepository.save(existingTicketType);
                    return toDTO(updatedTicketType);
                });
    }

    public void deleteTicketType(Long id) {
        if (!ticketTypeRepository.existsById(id)) {
            throw new RuntimeException("Ticket type not found");
        }
        ticketTypeRepository.deleteById(id);
    }

    // Convert TicketType to TicketTypeDTO
    private TicketTypeDTO toDTO(TicketType ticketType) {
        return new TicketTypeDTO(
                ticketType.getTicketTypeId(),
                ticketType.getTicketTypeName());
    }
}
