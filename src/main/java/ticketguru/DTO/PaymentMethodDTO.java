package ticketguru.DTO;

import jakarta.validation.constraints.NotBlank;

public class PaymentMethodDTO {

    private Long id;

    @NotBlank(message = "Payment method name must not be blank")
    private String name;

    public PaymentMethodDTO() {}

    public PaymentMethodDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}