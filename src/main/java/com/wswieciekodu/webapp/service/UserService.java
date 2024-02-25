package com.wswieciekodu.webapp.service;

import com.wswieciekodu.webapp.model.User;
import com.wswieciekodu.webapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Long createUser(User user) {
        return userRepository.save(user).getId();
    }

    public List<User> getUserByGender(String gender) {
        return userRepository.findByGender(gender);
    }
}
