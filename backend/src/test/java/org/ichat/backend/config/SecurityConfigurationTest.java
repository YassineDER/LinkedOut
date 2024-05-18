package org.ichat.backend.config;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@SpringBootTest
@Slf4j
class SecurityConfigurationTest {
    @Autowired
    private WebApplicationContext context;

    @Test
    @WithMockUser(authorities = "ADMIN", username = "admin")
    void testAuthorizedRequest() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(springSecurity())
                .build();

        mockMvc.perform(MockMvcRequestBuilders.get("/api/auth/status"))
                .andDo(result -> log.info(result.getResponse().getContentAsString()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    void testErrorRequest() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(springSecurity())                .build();

        mockMvc.perform(MockMvcRequestBuilders.get("/api/jobseeker/id/0"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }


    @Test
    void testNoAuthRequest() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(springSecurity())
                .build();
        mockMvc.perform(MockMvcRequestBuilders.get("/api/user/all"))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

}