package com.example.user_service.integration.infrastructure.http;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
public class UserControllerIT {

    @Autowired
    MockMvc mockMvc;

    @Test
    void shouldCreateUserWithStatusCreated() throws Exception {

        mockMvc.perform(post("/api/v1/users").contentType(MediaType.APPLICATION_JSON).content("""
                    {
                      "username": "user_valid",
                      "email": "user_valid@test.de"
                    }
                """)).andExpect(status().isCreated());

    }

    @Test
    void shouldNotCreateUserAndReturnStatusBadRequest() throws Exception {

        mockMvc
                .perform(post("/api/v1/users").contentType(MediaType.APPLICATION_JSON).content("""
                            {
                              "username": "",
                              "email": "user1@test.de"
                            }
                        """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Invalid Request Data"));
    }

    @Test
    void shouldNotCreateUserAndReturnUsernameAlreadyExistsAndStatusConflict() throws Exception {

        mockMvc.perform(post("/api/v1/users").contentType(MediaType.APPLICATION_JSON).content("""
                    {
                      "username": "user_exists",
                      "email": "user_exists_1@test.de"
                    }
                """)).andExpect(status().isCreated());

        mockMvc
                .perform(post("/api/v1/users").contentType(MediaType.APPLICATION_JSON).content("""
                            {
                              "username": "user_exists",
                              "email": "user_exists_2@test.de"
                            }
                        """))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message").value("Username already exists"));
    }

    @Test
    void shouldNotCreateUserAndReturnUserEmailAlreadyExistsAndStatusConflict() throws Exception {

        mockMvc.perform(post("/api/v1/users").contentType(MediaType.APPLICATION_JSON).content("""
                    {
                      "username": "user_email_exists_1",
                      "email": "user_email_exists_1@test.de"
                    }
                """)).andExpect(status().isCreated());

        mockMvc
                .perform(post("/api/v1/users").contentType(MediaType.APPLICATION_JSON).content("""
                            {
                              "username": "user_email_exists_2",
                              "email": "user_email_exists_1@test.de"
                            }
                        """))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message").value("Email already exists"));
    }
}

