package org.ichat.backend.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityConfigurationTest {

    @Autowired
    private MockMvc mockMvc;

    @WithMockUser(roles = "ADMIN")
    @Test
    public void adminAccessToAdminEndpoint() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/admin/test"))
                .andExpect(status().isOk());
    }

    @WithMockUser(roles = "JOBSEEKER")
    @Test
    public void jobseekerAccessToAdminEndpoint() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/admin/test"))
                .andExpect(status().isForbidden());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/company/test"))
                .andExpect(status().isForbidden());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/jobseeker/test"))
                .andExpect(status().isOk());
    }

    @WithMockUser(roles = "COMPANY")
    @Test
    public void companyAccessToAdminEndpoint() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/admin/test"))
                .andExpect(status().isForbidden());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/jobseeker/test"))
                .andExpect(status().isForbidden());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/jobseeker/test"))
                .andExpect(status().isForbidden());
    }

    @WithMockUser(roles = "ADMIN")
    @Test
    public void adminAccessToJobseekerEndpoint() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/jobseeker/test"))
                .andExpect(status().isForbidden());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/company/test"))
                .andExpect(status().isForbidden());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/company/test"))
                .andExpect(status().isForbidden());
    }

    @Test
    public void anonymousAccessToProtectedEndpoint() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/admin/test"))
                .andExpect(status().isUnauthorized());
    }
}