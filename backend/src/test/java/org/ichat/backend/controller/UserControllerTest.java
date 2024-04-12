package org.ichat.backend.controller;

import org.ichat.backend.model.tables.Jobseeker;
import org.ichat.backend.model.tables.User;
import org.ichat.backend.service.ITwoFactorAuthService;
import org.ichat.backend.service.IUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class UserControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private IUserService userService;
    @MockBean
    private ITwoFactorAuthService twoFactorService;
    private Jobseeker user;

    @BeforeEach
    void setUp() {
        user = new Jobseeker();
        user.setUser_id(1L);
        user.setEmail("xx@yy.com");
        user.setUsername("user");
        user.setPassword("password");
        user.setFirst_name("first");
        user.setLast_name("last");
    }

    @Test
    void all() throws Exception {
        // TODO
        List<User> users = Collections.singletonList(user);
        users.forEach(u -> u.setPassword(null));
        when(userService.findAll()).thenReturn(users);
        mvc.perform(get("/api/user/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
        verify(userService, times(1)).findAll();
    }

    @Test
    void delete() throws Exception {
        doNothing().when(userService).deleteBy(1L);
        mvc.perform(MockMvcRequestBuilders.delete("/api/user/id/1"))
                .andExpect(status().isNoContent());
        verify(userService, times(1)).deleteBy(1L);
    }

    @Test
    void enableMfa() throws Exception {
        when(userService.findBy(1L)).thenReturn(user);
        when(twoFactorService.generateMfaSecret()).thenReturn("secret");
        when(userService.update(any(Long.class), any(User.class))).thenReturn(user);

        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(new UsernamePasswordAuthenticationToken(user, null));
        SecurityContextHolder.setContext(securityContext);

        mvc.perform(post("/api/user/enable-mfa"))
                .andExpect(status().isNoContent());

        assertTrue(user.getUsing_mfa());
        verify(userService, times(1)).update(any(Long.class), any(User.class));
    }

    @Test
    void enableMfa_alreadyEnabled() throws Exception {
        user.setUsing_mfa(true);
        when(userService.findBy(1L)).thenReturn(user);

        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(new UsernamePasswordAuthenticationToken(user, null));
        SecurityContextHolder.setContext(securityContext);

        mvc.perform(post("/api/user/enable-mfa"))
                .andExpect(status().isBadRequest());
    }
}