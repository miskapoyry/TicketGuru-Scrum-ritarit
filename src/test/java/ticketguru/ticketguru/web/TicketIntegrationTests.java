package ticketguru.ticketguru.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import jakarta.transaction.Transactional;
import ticketguru.domain.Ticket;
import ticketguru.repository.TicketRepository;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@ActiveProfiles("test")
@Import(TestSecurityConfig.class)
public class TicketIntegrationTests {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    // Testataan, että Liquibasen konfiguraatiot OK ja yhteys tietokantaan olemassa
    @Test
    public void testLiquibaseInitialization() {
        List<String> executedChangesets = ticketRepository.findAll()
                .stream()
                .map(Ticket::toString)
                .toList();
        System.out.println("Executed change sets: " + executedChangesets);
        assertFalse(executedChangesets.isEmpty());
    }

    @Test
    public void testGetAllTickets() throws Exception {
        mockMvc.perform(get("/api/tickets"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].ticketId").value(1))
                .andExpect(jsonPath("$[0].ticketNumber").value("TICKET-1001"))
                .andExpect(jsonPath("$[1].ticketId").value(2))
                .andExpect(jsonPath("$[1].ticketNumber").value("TICKET-1002"))
                .andExpect(jsonPath("$[2].ticketId").value(3))
                .andExpect(jsonPath("$[2].ticketNumber").value("TICKET-1003"));
    }

    @Test
    @Transactional
    public void testFetchTicketByTicketNumber() {

        Ticket ticket = ticketRepository.findByTicketNumber("TICKET-1001").orElse(null);

        assertThat(ticket).isNotNull();
        assertThat(ticket.getTicketId()).isEqualTo(1L);
        assertThat(ticket.getEventId()).isEqualTo(1L);
        assertThat(ticket.getTicketTypeId()).isEqualTo(1L);
        assertThat(ticket.getSale().getSaleId()).isEqualTo(1L);
        assertThat(ticket.getSaleTimestamp()).isNotNull();
        assertThat(ticket.isUsed()).isFalse();
    }

    @Test
    public void testGetTicketsByEventId() throws Exception {
        mockMvc.perform(get("/api/tickets")
                .param("eventId", "1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].eventId").value(1));
    }

    @Test
    public void shouldReturnNotFoundForInvalidTicketNumber() throws Exception {
        String invalidTicketNumber = "99999";

        mockMvc.perform(get("/tickets/number/" + invalidTicketNumber)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()); // 404 Not Found
    }

    // Merkitään lippu käytetyksi
    @Test
    @Transactional
    public void testMarkTicketAsUsedByNumber() throws Exception {

        String ticketNumber = "TICKET-1001";

        // Tehdään PUT lipun päivittämikseksi käytetyksi
        mockMvc.perform(put("/api/tickets/{ticketNumber}/use", ticketNumber)
                .param("used", "true") // Käytetyksi
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.used", is(true))) // Tarkistetaan, että on käytetty
                .andExpect(jsonPath("$.usedTimestamp").exists()); // Tarkistetaan, että Timestamp on olemassa
    }

}