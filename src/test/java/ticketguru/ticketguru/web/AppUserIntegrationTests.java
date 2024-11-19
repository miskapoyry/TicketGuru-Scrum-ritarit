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
public class AppUserIntegrationTests {

        @Autowired
        private WebApplicationContext webApplicationContext;

        private MockMvc mockMvc;

        @BeforeEach
        public void setup() {
                this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        }

        @Test
        @Transactional
        public void testCreateUser() throws Exception {
                String userJson = "{"
                                + "\"username\": \"testuser\", "
                                + "\"passwordHash\": \"password123\", "
                                + "\"roleId\": 1"
                                + "}";

                mockMvc.perform(post("/api/users")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(userJson))
                                .andExpect(status().isCreated())
                                .andExpect(jsonPath("$.username", is("testuser")))
                                .andExpect(jsonPath("$.roleId", is(1)));
        }

        @Test
        public void testGetAllUsers() throws Exception {
                mockMvc.perform(get("/api/users"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$").isArray());
        }

        @Test
        public void testGetUserById() throws Exception {
                mockMvc.perform(get("/api/users/1"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.userId", is(1)));
        }

        @Test
        public void testGetUserByIdNotFound() throws Exception {
                mockMvc.perform(get("/api/users/999"))
                                .andExpect(status().isNotFound())
                                .andExpect(jsonPath("$.message", is("User with ID 999 not found")));
        }

        @Test
        @Transactional
        public void testUpdateUser() throws Exception {
                String updatedUserJson = "{"
                                + "\"username\": \"updateduser\", "
                                + "\"passwordHash\": \"newpassword123\", "
                                + "\"roleId\": 2"
                                + "}";

                mockMvc.perform(put("/api/users/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(updatedUserJson))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.username", is("updateduser")))
                                .andExpect(jsonPath("$.roleId", is(2)));
        }

        @Test
        @Transactional
        public void testDeleteUser() throws Exception {
                mockMvc.perform(delete("/api/users/1"))
                                .andExpect(status().isNoContent());

                mockMvc.perform(get("/api/users/1"))
                                .andExpect(status().isNotFound())
                                .andExpect(jsonPath("$.message", is("User with ID 1 not found")));
        }

        @Test
        public void testDeleteUserNotFound() throws Exception {
                mockMvc.perform(delete("/api/users/999"))
                                .andExpect(status().isNotFound())
                                .andExpect(jsonPath("$.message", is("User with ID 999 not found")));
        }

        @Test
        public void testCreateUserWithMissingUsername() throws Exception {
                String userJson = "{"
                                + "\"passwordHash\": \"password123\", "
                                + "\"roleId\": 1"
                                + "}";

                mockMvc.perform(post("/api/users")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(userJson))
                                .andExpect(status().isBadRequest())
                                .andExpect(jsonPath("$.message", is("Username cannot be blank")));
        }

        @Test
        public void testCreateUserWithMissingPassword() throws Exception {
                String userJson = "{"
                                + "\"username\": \"newuser\", "
                                + "\"roleId\": 1"
                                + "}";

                mockMvc.perform(post("/api/users")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(userJson))
                                .andExpect(status().isBadRequest())
                                .andExpect(jsonPath("$.message", is("Password cannot be blank")));
        }
}
