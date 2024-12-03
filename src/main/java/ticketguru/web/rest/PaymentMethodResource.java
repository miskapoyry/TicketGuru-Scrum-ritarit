package ticketguru.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ticketguru.DTO.PaymentMethodDTO;
import ticketguru.service.PaymentMethodService;
import ticketguru.exception.ResourceNotFoundException;

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
}