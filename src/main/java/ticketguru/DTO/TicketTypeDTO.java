package ticketguru.DTO;

import jakarta.validation.constraints.NotBlank;

public class TicketTypeDTO {

    private Long ticketTypeId;

    @NotBlank(message = "Ticket type name is required")
    private String ticketTypeName;

    public TicketTypeDTO() {}

    public TicketTypeDTO(Long ticketTypeId, String ticketTypeName) {
        this.ticketTypeId = ticketTypeId;
        this.ticketTypeName = ticketTypeName;
    }

    // Getters and setters
    public Long getTicketTypeId() {
        return ticketTypeId;
    }

    public void setTicketTypeId(Long ticketTypeId) {
        this.ticketTypeId = ticketTypeId;
    }

    public String getTicketTypeName() {
        return ticketTypeName;
    }

    public void setTicketTypeName(String ticketTypeName) {
        this.ticketTypeName = ticketTypeName;
    }
}