package com.olieniev.taskmanagement.controller;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.olieniev.taskmanagement.dto.user.UserLoginRequestDto;
import com.olieniev.taskmanagement.dto.user.UserRegistrationRequestDto;
import com.olieniev.taskmanagement.dto.user.UserResponseDto;
import com.olieniev.taskmanagement.model.RoleName;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthControllerTest {
    protected static MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeAll
    static void beforeAll(@Autowired WebApplicationContext applicationContext) {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(applicationContext)
                .apply(springSecurity())
                .build();
    }

    @Test
    @DisplayName("""
        User registration with valid dto is successful        
            """)
    void register_validRequestDto_success() throws Exception {
        UserRegistrationRequestDto requestDto = new UserRegistrationRequestDto(
                "test username",
                "12345678",
                "test@email.com",
                "test name",
                "test surname"
        );
        String jsonRequest = objectMapper.writeValueAsString(requestDto);
        MvcResult result = mockMvc.perform(post("/auth/registration")
                    .content(jsonRequest)
                    .contentType(MediaType.APPLICATION_JSON)
            )
                .andExpect(status().isOk())
                .andReturn();
        UserResponseDto actual = objectMapper.readValue(
                result.getResponse().getContentAsString(), UserResponseDto.class
        );
        UserResponseDto expected = new UserResponseDto(
                null,
                "test username",
                "test@email.com",
                "test name",
                "test surname",
                RoleName.ROLE_USER
        );
        Assertions.assertNotNull(actual);
        Assertions.assertNotNull(actual.id());
        reflectionEquals(expected, actual, "id");
    }

    @Test
    @Sql(scripts = "classpath:database/user/insert-two-users.sql")
    @Sql(scripts = "classpath:database/user/delete-users.sql",
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @DisplayName("""
        User login with valid credentials is successful        
            """)
    void login_validRequestDto_success() throws Exception {
        UserLoginRequestDto requestDto = new UserLoginRequestDto(
                "testusername2",
                "12345678"
        );
        String jsonRequest = objectMapper.writeValueAsString(requestDto);
        mockMvc.perform(post("/auth/login")
                        .content(jsonRequest)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
    }
}
