package ticketguru.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;
import ticketguru.DTO.PaymentMethodDTO;
import ticketguru.domain.PaymentMethod;
import ticketguru.exception.DuplicateResourceException;
import ticketguru.exception.ResourceNotFoundException;
import ticketguru.repository.PaymentMethodRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PaymentMethodService {

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    // Get all payment methods
    public List<PaymentMethodDTO> getAllPaymentMethods() {
        List<PaymentMethod> paymentMethods = paymentMethodRepository.findAll(); // Haetaan kaikki maksutavat

        return paymentMethods.stream()
                .map(this::toDTO) // mappaus DTO:ksi
                .collect(Collectors.toList()); // Listalle
    }

    // Haku ID:llä
    public Optional<PaymentMethodDTO> getPaymentMethodById(Long id) {
        return paymentMethodRepository.findById(id)
                .map(this::toDTO); // Jos löytyy, DTO:ksi
    }

    // Luodaan uusi
    public PaymentMethodDTO createPaymentMethod(@Valid PaymentMethodDTO paymentMethodDTO) {
        // Tarkistetaan, löytyykö nimellä maksutapa
        if (paymentMethodRepository.existsByPaymentMethodName(paymentMethodDTO.getName())) {
            throw new DuplicateResourceException("Payment method with the same name already exists");
        }
        // Luodaan uusi maksutapa
        PaymentMethod paymentMethod = new PaymentMethod(paymentMethodDTO.getName());
        PaymentMethod savedPaymentMethod = paymentMethodRepository.save(paymentMethod); // Tallennus repositorioon
        return toDTO(savedPaymentMethod); // Uuden maksutavan DTO
    }

    // päivitetään maksutapa
    public PaymentMethodDTO updatePaymentMethod(Long id, @Valid PaymentMethodDTO paymentMethodDTO) {
        PaymentMethod existingPaymentMethod = paymentMethodRepository.findById(id) // Tarkistetaan, löytyykö
                .orElseThrow(() -> new ResourceNotFoundException("Payment method with ID " + id + " not found"));

        // Tarkistus duplikaattien varalta
        if (paymentMethodRepository.existsByPaymentMethodName(paymentMethodDTO.getName()) &&
                !existingPaymentMethod.getPaymentMethodName().equals(paymentMethodDTO.getName())) {
            throw new DuplicateResourceException("Payment method with the same name already exists");
        }

        // päivitetään nimi
        existingPaymentMethod.setPaymentMethodName(paymentMethodDTO.getName());

        // Tallennetaan
        PaymentMethod updatedPaymentMethod = paymentMethodRepository.save(existingPaymentMethod);

        // palautetaan DTO
        return toDTO(updatedPaymentMethod);
    }

    // Poistetaan id:llä
    public void deletePaymentMethod(Long id) {
        if (!paymentMethodRepository.existsById(id)) {
            throw new ResourceNotFoundException("Payment method with ID " + id + " not found");
        }
        paymentMethodRepository.deleteById(id); // Poistetaan repositoriosta
    }

    // Convert PaymentMethod to PaymentMethodDTO
    private PaymentMethodDTO toDTO(PaymentMethod paymentMethod) {
        return new PaymentMethodDTO(
                paymentMethod.getPaymentMethodId(),
                paymentMethod.getPaymentMethodName());
    }
}
