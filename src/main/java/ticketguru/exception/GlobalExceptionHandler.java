package ticketguru.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    // 404 erroreille
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        // Luo ErrorResponse, jossa on viesti, statuskoodi, aikaleima sekä path
        ErrorResponse errorResponse = new ErrorResponse(
            ex.getMessage(),
            HttpStatus.NOT_FOUND.value(),
            LocalDateTime.now(),
            request.getDescription(false)
        );
        // Palautetaan se
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    // 400 erroreille (@Valid annotaatio)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex, WebRequest request) {
        Map<String, Object> response = new HashMap<>();
        Map<String, String> messages = new HashMap<>();
        
        // Käydään kaikki virheet läpi ja lisätään ne mappiin
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            messages.put(error.getField(), error.getDefaultMessage());
        }
        
        // Lisää tiedot responseen
        response.put("errors", messages);
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("path", request.getDescription(false));
        response.put("timestamp", LocalDateTime.now());
        
        // Palautetaan vastaus, jossa on tiedot
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}