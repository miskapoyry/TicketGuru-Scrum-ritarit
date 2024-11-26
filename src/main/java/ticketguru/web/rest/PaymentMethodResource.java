package ticketguru.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ticketguru.DTO.PaymentMethodDTO;
import ticketguru.service.PaymentMethodService;
import ticketguru.exception.DuplicateResourceException;
import ticketguru.exception.ResourceNotFoundException;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/paymentmethods")
public class PaymentMethodResource {

    @Autowired
    private PaymentMethodService paymentMethodService;

    // Get all
    @GetMapping
    public List<PaymentMethodDTO> getAllPaymentMethods() {
        return paymentMethodService.getAllPaymentMethods();
    }

    // Get by id
    @GetMapping("/{id}")
    public ResponseEntity<PaymentMethodDTO> getPaymentMethodById(@PathVariable Long id) {
        PaymentMethodDTO paymentMethod = paymentMethodService.getPaymentMethodById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment method with ID " + id + " not found"));
        return ResponseEntity.ok(paymentMethod);
    }

    // Post new
    @PostMapping
    public ResponseEntity<PaymentMethodDTO> createPaymentMethod(@Valid @RequestBody PaymentMethodDTO paymentMethodDTO) {
        // Tarkistetaan, ettei ole jo olemassa
        if (paymentMethodService.getAllPaymentMethods().stream()
                .anyMatch(paymentMethod -> paymentMethod.getName().equals(paymentMethodDTO.getName()))) {
            throw new DuplicateResourceException("Payment method already exists: " + paymentMethodDTO.getName());
        }

        PaymentMethodDTO createdPaymentMethod = paymentMethodService.createPaymentMethod(paymentMethodDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPaymentMethod);
    }

    // Put, päivitys
    @PutMapping("/{id}")
    public ResponseEntity<PaymentMethodDTO> updatePaymentMethod(@PathVariable Long id,
            @Valid @RequestBody PaymentMethodDTO paymentMethodDTO) {
        // duplikaattitarkistus
        if (paymentMethodService.getAllPaymentMethods().stream()
                .anyMatch(paymentMethod -> !paymentMethod.getId().equals(id)
                        && paymentMethod.getName().equals(paymentMethodDTO.getName()))) {
            throw new DuplicateResourceException("Payment method already exists: " + paymentMethodDTO.getName());
        }

        // Palautetaan päivitettt DTO
        PaymentMethodDTO updatedPaymentMethod = paymentMethodService.updatePaymentMethod(id, paymentMethodDTO);
        return ResponseEntity.ok(updatedPaymentMethod);
    }

    // Delete by ids
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePaymentMethod(@PathVariable Long id) {
        paymentMethodService.deletePaymentMethod(id);
        return ResponseEntity.noContent().build();
    }
}
