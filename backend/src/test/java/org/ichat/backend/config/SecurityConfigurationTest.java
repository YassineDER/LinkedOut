package org.ichat.backend.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@SpringBootTest
@AutoConfigureMockMvc
public class SecurityConfigurationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void unauthenticatedRequestsToProtectedEndpointShouldReturnUnauthorized() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/user/all"))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "amina.goncalves@example.com", password = "124")
    public void authenticatedRequestsToProtectedEndpointShouldBeAllowed() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/user/all"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    @Test
    public void unauthenticatedRequestsToUnprotectedEndpointShouldBeAllowed() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/login"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void unauthenticatedRequestsToUnprotectedEndpointShouldBeAllowed2() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/register"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void requestsWithInvalidCredentialsShouldReturnUnauthorized() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/user/all")
                        .header("Authorization", "Bearer invalid_token"))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }


}