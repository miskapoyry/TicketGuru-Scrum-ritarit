package ticketguru.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;
import ticketguru.DTO.TicketTypeDTO;
import ticketguru.domain.TicketType;
import ticketguru.exception.DuplicateResourceException;
import ticketguru.exception.ResourceNotFoundException;
import ticketguru.repository.TicketTypeRepository;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TicketTypeService {

    @Autowired
    private TicketTypeRepository ticketTypeRepository;

    public List<TicketTypeDTO> getAllTicketTypes() {
        List<TicketType> ticketTypes = ticketTypeRepository.findAll(); // Haetaan kaikki liput repositoriosta
        return ticketTypes.stream()
                .map(this::toDTO) // muutetaan DTO:ksi
                .collect(Collectors.toList()); //Kerätään listaksi
    }

    public Optional<TicketTypeDTO> getTicketTypeById(Long id) {
        return ticketTypeRepository.findById(id).map(this::toDTO); // Etsitään ID:llä, muutetaan DTO:ksi
    }

    public TicketTypeDTO createTicketType(@Valid TicketTypeDTO ticketTypeDTO) {
    if (ticketTypeRepository.existsByTicketTypeName(ticketTypeDTO.getTicketTypeName())) { // jos löytyy saman nimisiä... (tarkistus repositoriossa olevalla metodilla)
        throw new DuplicateResourceException("Ticket type with the same name already exists"); //... heitetään duplikaattierrori
    }
    TicketType ticketType = new TicketType(ticketTypeDTO.getTicketTypeName()); // Luodaan uusi lipputyyppi DTO:sta
    TicketType savedTicketType = ticketTypeRepository.save(ticketType); // tallennetaan repositorioon
    return toDTO(savedTicketType); // muutetaan DTO:ksi, palautetaan
    }

    public Optional<TicketTypeDTO> updateTicketType(Long id, @Valid TicketTypeDTO ticketTypeDTO) {
        return ticketTypeRepository.findById(id) // etsitään ensin ID:llä
                .map(existingTicketType -> {
                    if (ticketTypeRepository.existsByTicketTypeName(ticketTypeDTO.getTicketTypeName()) && // ... jos löytyy saman nimisiä
                            !existingTicketType.getTicketTypeName().equals(ticketTypeDTO.getTicketTypeName())) { //... Jos uusi nimi on tasan sama kuin vanha nimi, päivitys OK 
                        throw new DuplicateResourceException("Ticket type with the same name already exists"); // jos löytyy lipputyyppi samalla nimellä, duplikaattivirhe
                    }

                    existingTicketType.setTicketTypeName(ticketTypeDTO.getTicketTypeName()); // päivitetään nimi
                    TicketType updatedTicketType = ticketTypeRepository.save(existingTicketType); // tallennetaan repositorioon
                    return toDTO(updatedTicketType);
                });
    }

   public void deleteTicketType(Long id) {
    if (!ticketTypeRepository.existsById(id)) { //tarkistetaan id:llä, löytyykö
        throw new ResourceNotFoundException("Ticket type with ID " + id + " not found"); // jos ei löydy, tämä virhe
    }
    ticketTypeRepository.deleteById(id); // jos löytyy, poistetaan repositoriosta
    }

    // Convert TicketType to TicketTypeDTO
    private TicketTypeDTO toDTO(TicketType ticketType) {
        return new TicketTypeDTO(
                ticketType.getTicketTypeId(),
                ticketType.getTicketTypeName());
    }
}
