package com.wswieciekodu.webapp.repository;

import com.wswieciekodu.webapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByGender(String gender);

    User findByEmail(String email);
}
