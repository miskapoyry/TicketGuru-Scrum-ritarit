package ticketguru.exception;
// Ihan vaan basic viesti erroreista, sisällön voi määritellä vapaasti
public class ErrorResponse {
    private String message;

    public ErrorResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}