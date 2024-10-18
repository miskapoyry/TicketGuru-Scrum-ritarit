package ticketguru.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ticketguru.DTO.SaleDTO;
import ticketguru.DTO.SaleSummaryDTO;
import ticketguru.service.SaleService;
import ticketguru.repository.AppUserRepository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/sales")
public class SaleResource {

    @Autowired
    private SaleService saleService;
    @Autowired
    private AppUserRepository appUserRepository;

    @PostMapping
    public ResponseEntity<SaleDTO> createSale(@RequestBody SaleDTO saleDTO) {
        SaleDTO createdSale = saleService.createSale(saleDTO);
        return ResponseEntity.ok(createdSale);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SaleDTO> updateSale(@PathVariable Long id, @RequestBody SaleDTO saleDTO) {
        SaleDTO updatedSale = saleService.updateSale(id, saleDTO);
        return ResponseEntity.ok(updatedSale);
    }

    @GetMapping
    public ResponseEntity<?> getAllSales(@RequestParam(required = false) Long userId) {
        if (userId != null && !appUserRepository.existsById(userId)) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "User not found with given ID");
            return ResponseEntity.status(404).body(errorResponse);
        }
        List<SaleSummaryDTO> sales = saleService.getAllSales(userId);
        return ResponseEntity.ok(sales);
    }

    @GetMapping("/search")
    public ResponseEntity<?> getSales(@RequestParam(required = false) String userId,
            @RequestParam(required = false) String saleIds) {
        if (userId != null) {
            List<String> userIds = Arrays.asList(userId.split(","));
            List<String> nonExistentUserIds = userIds.stream()
                    .map(String::trim)
                    .filter(id -> !id.isEmpty())
                    .filter(id -> !appUserRepository.existsById(Long.parseLong(id)))
                    .collect(Collectors.toList());

            if (!nonExistentUserIds.isEmpty()) {
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("message",
                        "User(s) not found with given ID(s): " + String.join(", ", nonExistentUserIds));
                return ResponseEntity.status(404).body(errorResponse);
            }

            List<SaleDTO> sales = userIds.stream()
                    .map(String::trim)
                    .filter(id -> !id.isEmpty())
                    .map(id -> saleService.getSalesByUserId(Long.parseLong(id)))
                    .flatMap(List::stream)
                    .collect(Collectors.toList());

            if (sales.isEmpty()) {
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("message", "No sales found for user ID(s): " + userId);
                return ResponseEntity.status(404).body(errorResponse);
            }

            return ResponseEntity.ok(sales);
        } else if (saleIds != null) {
            List<Long> saleIdList;
            try {
                saleIdList = Arrays.stream(saleIds.split(","))
                        .map(String::trim)
                        .filter(id -> !id.isEmpty())
                        .map(Long::parseLong)
                        .collect(Collectors.toList());
            } catch (NumberFormatException e) {
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("message", "Invalid sale ID format in: " + saleIds);
                return ResponseEntity.status(400).body(errorResponse);
            }

            List<SaleDTO> sales = saleService.getSales(saleIdList);
            if (sales.isEmpty()) {
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("message", "No sales found for sale IDs: " + saleIds);
                return ResponseEntity.status(404).body(errorResponse);
            }
            return ResponseEntity.ok(sales);
        } else {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "Either userId or saleIds must be provided");
            return ResponseEntity.status(400).body(errorResponse);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSale(@PathVariable Long id) {
        saleService.deleteSale(id);
        return ResponseEntity.noContent().build();
    }
}