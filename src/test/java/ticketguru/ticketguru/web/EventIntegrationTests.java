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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@ActiveProfiles("test")
@Import(TestSecurityConfig.class)
public class EventIntegrationTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    // Kaikkien tapahtumien haku. Varmistus, että palautettu tulos on JSON array ja
    // status 200
    @Test
    public void testGetAllEvents() throws Exception {
        mockMvc.perform(get("/api/events"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());
    }

    // Tapahtuman haku id:llä
    @Test
    public void testGetEventById() throws Exception {
        Long eventId = 1L;
        mockMvc.perform(get("/api/events/{id}", eventId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.eventId", is(1)));
    }

    // Uuden tapahtuman luominen - tarkistus, että statuskoodi 201 ja tapahtuman
    // nimi oikea
    @Test
    public void testCreateEvent() throws Exception {
        String newEvent = """
                {
                    "userId": 1,
                    "eventName": "New Event",
                    "eventDate": "2024-12-10T00:00:00.000+00:00",
                    "location": "Helsinki",
                    "totalTickets": 100,
                    "availableTickets": 80,
                    "eventTicketTypes": [
                        {
                            "ticketTypeId": 2,
                            "ticketQuantity": 50,
                            "price": 50.0,
                            "ticketTypeName": "Regular"
                        }
                    ]
                }
                """;

        mockMvc.perform(post("/api/events")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newEvent))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.eventName").value("New Event"));
    }

    // Tapahtuman päivittäminen, tarkistus, että nimi päivittynyt
    @Test
    public void testUpdateEvent() throws Exception {
        Long eventId = 1L;
        String updatedEvent = """
                {
                    "userId": 1,
                    "eventName": "Updated Event",
                    "eventDate": "2024-12-15T00:00:00.000+00:00",
                    "location": "Espoo",
                    "totalTickets": 150,
                    "availableTickets": 120,
                    "eventTicketTypes": [
                        {
                            "eventTicketTypeId": 1,
                            "eventId": 1,
                            "ticketTypeId": 2,
                            "ticketQuantity": 50,
                            "price": 50.0,
                            "eventName": "New Event",
                            "ticketTypeName": "Regular"
                        }
                    ]
                }
                """;

        mockMvc.perform(put("/api/events/1", eventId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedEvent)).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.eventName", is("Updated Event")));
    }

    // Testataan tapahtuman etsimistä olemattomalla id:llä
    @Test
    public void testEventNotFound() throws Exception {
        Long invalidEventId = 999L;

        mockMvc.perform(get("/api/events/{id}", invalidEventId))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", is("Event with ID " + invalidEventId + " not found")));
    }
}