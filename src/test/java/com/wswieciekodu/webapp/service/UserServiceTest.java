package com.wswieciekodu.webapp.service;

import com.wswieciekodu.webapp.model.User;
import com.wswieciekodu.webapp.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void shouldGetUserByEmailFromRepository() {
        // given
        String email = "john.doe@example.com";
        ZonedDateTime now = ZonedDateTime.now();
        User user = new User(1L, "John", "Doe", "john.doe@example.com", 21, "male", now, new HashSet<>());
        when(userRepository.findByEmail(email)).thenReturn(user);

        // when
        User returnedUser = userService.getUserByEmail(email);

        // then
        verify(userRepository, times(1)).findByEmail(eq(email));
        assertEquals(user, returnedUser);
    }

    @Test
    public void shouldCreateUser() {
        // given
        ZonedDateTime now = ZonedDateTime.now();
        User user = new User(1L, "John", "Doe", "john.doe@example.com", 21, "male", now, new HashSet<>());
        when(userRepository.save(eq(user))).thenReturn(user);

        // when
        userService.createUser(user);

        // then
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testGetUserByGender() {
        // given
        String gender = "male";
        ZonedDateTime now = ZonedDateTime.now();
        User user1 = new User(1L, "John", "Doe", "john.doe@example.com", 21, "male", now, new HashSet<>());
        User user2 = new User(2L, "Jo", "Do", "jo.do@example.com", 25, "male", now, new HashSet<>());
        List<User> users = List.of(user1, user2);
        when(userRepository.findByGender(gender)).thenReturn(users);

        // when
        List<User> usersByGender = userService.getUserByGender(gender);

        // then
        verify(userRepository, times(1)).findByGender(eq(gender));
        assertEquals(users, usersByGender);
    }
}