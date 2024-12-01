package ticketguru.ticketguru.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import jakarta.transaction.Transactional;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.hamcrest.Matchers.*;

@SpringBootTest
@ActiveProfiles("test")
@Import(TestSecurityConfig.class)
public class SaleIntegrationTests {

        @Autowired
        private WebApplicationContext webApplicationContext;

        private MockMvc mockMvc;

        @BeforeEach
        public void setup() {
                this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        }

        // Uuden myyntitapahtuman luominen & tarkistus, että tiedot tallentuneet
        // toivotusti. Tapahtumassa kaksi lippua.
        @Test
        @Transactional
        public void testCreateSale() throws Exception {
                String saleJson = "{"
                                + "\"userId\": 1, "
                                + "\"paymentMethodId\": 1, "
                                + "\"tickets\": ["
                                + "    {"
                                + "        \"eventId\": \"1\", "
                                + "        \"ticketTypeId\": \"1\", "
                                + "        \"quantity\": \"2\", "
                                + "        \"used\": \"false\" "
                                + "    }"
                                + "]"
                                + "}";

                mockMvc.perform(post("/api/sales")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(saleJson))
                                .andExpect(status().isCreated())
                                .andExpect(jsonPath("$.userId", is(1)))
                                .andExpect(jsonPath("$.paymentMethodId", is(1)))
                                .andExpect(jsonPath("$.tickets", hasSize(2)))
                                .andExpect(jsonPath("$.tickets[0].eventId", is(1)))
                                .andExpect(jsonPath("$.tickets[0].ticketTypeId", is(1)))
                                .andExpect(jsonPath("$.tickets[0].quantity", is(1)))
                                .andExpect(jsonPath("$.tickets[0].used", is(false)))
                                .andExpect(jsonPath("$.tickets[1].eventId", is(1)))
                                .andExpect(jsonPath("$.tickets[1].ticketTypeId", is(1)))
                                .andExpect(jsonPath("$.tickets[1].quantity", is(1)))
                                .andExpect(jsonPath("$.tickets[1].used", is(false)));
        }

        // Kaikkien myyntitapahtumien haku
        @Test
        public void testGetAllSales() throws Exception {
                mockMvc.perform(get("/api/sales"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$").isArray());
        }

        // Myynnin haku id:llä
        @Test
        public void testGetSaleById() throws Exception {
                mockMvc.perform(get("/api/sales/1"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.saleId", is(1)));
        }

        // Myynnin haku id:llä, jota ei ole
        @Test
        public void testGetSaleByIdNotFound() throws Exception {
                mockMvc.perform(get("/api/sales/999"))
                                .andExpect(status().isNotFound())
                                .andExpect(jsonPath("$.message", is("Sale not found with given ID: 999")));
        }

        // Myyntitapahtuman poisto
        @Transactional
        @Test
        public void testDeleteSale() throws Exception {
                mockMvc.perform(delete("/api/sales/1"))
                                .andExpect(status().isNoContent());

                mockMvc.perform(get("/api/sales/1"))
                                .andExpect(status().isNotFound());
        }

        // Myyntitapahtumien haku käyttäjän id:llä omasta endpointistaan
        @Test
        public void testSearchSalesByUserId() throws Exception {
                mockMvc.perform(get("/api/sales/search")
                                .param("userId", "1"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$").isArray());
        }

        // Myyntitapahtumien haku käyttäjän id:llä omasta endpointistaan id:llä, jota ei
        // ole
        @Test
        public void testSearchSalesByInvalidUserId() throws Exception {
                mockMvc.perform(get("/api/sales/search")
                                .param("userId", "999"))
                                .andExpect(status().isNotFound())
                                .andExpect(jsonPath("$.message", is("User(s) not found with given ID(s): 999")));
        }

        // Useiden myyntitapahtumien haku id:illä
        @Test
        public void testSearchSalesBySaleIds() throws Exception {
                mockMvc.perform(get("/api/sales/search")
                                .param("saleIds", "1,2"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$[0].saleId", is(1)))
                                .andExpect(jsonPath("$[1].saleId", is(2)));
        }

        // Useiden myyntitapahtumien haku olemattomilla id:illä
        @Test
        public void testSearchSalesByInvalidSaleIds() throws Exception {
                mockMvc.perform(get("/api/sales/search")
                                .param("saleIds", "999,1000"))
                                .andExpect(status().isNotFound())
                                .andExpect(jsonPath("$.message", is("No sales found for sale IDs: 999,1000")));
        }

        // Väärän muotoisilla id:illä haku
        @Test
        public void testSearchSalesInvalidSaleIdFormat() throws Exception {
                mockMvc.perform(get("/api/sales/search")
                                .param("saleIds", "invalid,ids"))
                                .andExpect(status().isBadRequest())
                                .andExpect(jsonPath("$.message", is("Invalid sale ID format in: invalid,ids")));
        }

        // search-endpointin haku ilman parametreja
        @Test
        public void testSearchSalesMissingParameters() throws Exception {
                mockMvc.perform(get("/api/sales/search"))
                                .andExpect(status().isBadRequest())
                                .andExpect(jsonPath("$.message", is("Either userId or saleIds must be provided")));
        }
}
