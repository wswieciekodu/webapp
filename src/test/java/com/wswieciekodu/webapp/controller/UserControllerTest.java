package com.wswieciekodu.webapp.controller;

import com.wswieciekodu.webapp.model.User;
import com.wswieciekodu.webapp.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void shouldReturnUserFromServiceWhenGetByEmail() throws Exception {
        ZonedDateTime now = ZonedDateTime.now();
        User user = new User(1L, "John", "Doe", "john.doe@example.com", 21, "male", now, new HashSet<>());
        when(userService.getUserByEmail("john.doe@example.com")).thenReturn(user);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/user/john.doe@example.com"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"))
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"))
                .andExpect(jsonPath("$.gender").value("male"))
                .andExpect(jsonPath("$.gender").value("male"))
                .andReturn();

        System.out.println();
    }

    @Test
    public void shouldReturnUsersFromServiceWhenGetByGender() throws Exception {
        ZonedDateTime now = ZonedDateTime.now();
        String nowFormatted = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        User user1 = new User(1L, "John", "Doe", "john.doe@example.com", 21, "male", now, new HashSet<>());
        User user2 = new User(2L, "Jan", "Ko", "jan.ko@example.com", 22, "male", now, new HashSet<>());
        List<User> users = Arrays.asList(user1, user2);
        when(userService.getUserByGender("male")).thenReturn(users);

        mockMvc.perform(MockMvcRequestBuilders.get("/user?gender=male"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].email").value("john.doe@example.com"))
                .andExpect(jsonPath("$[0].firstName").value("John"))
                .andExpect(jsonPath("$[0].lastName").value("Doe"))
                .andExpect(jsonPath("$[0].gender").value("male"))
                .andExpect(jsonPath("$[0].age").value("21"))
                .andExpect(jsonPath("$[0].createdAt").value(nowFormatted))
                .andExpect(jsonPath("$[1].email").value("jan.ko@example.com"))
                .andExpect(jsonPath("$[1].firstName").value("Jan"))
                .andExpect(jsonPath("$[1].lastName").value("Ko"))
                .andExpect(jsonPath("$[1].gender").value("male"))
                .andExpect(jsonPath("$[1].age").value("22"))
                .andExpect(jsonPath("$[1].createdAt").value(nowFormatted));
    }

    @Test
    public void shouldCallCreateUserOneTimeWhenCreateUser() throws Exception {
        User user = new User(null, "John", "Doe", "john.doe@example.com", 21, "male", null, new HashSet<>());

        mockMvc.perform(MockMvcRequestBuilders.post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\":\"John\",\"lastName\":\"Doe\",\"email\":\"john.doe@example.com\",\"age\":\"21\",\"gender\":\"male\"}"))
                .andExpect(status().isOk());

        verify(userService, times(1)).createUser(eq(user));
    }
}