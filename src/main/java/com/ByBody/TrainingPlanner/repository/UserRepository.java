package com.ByBody.TrainingPlanner.repository;

import com.ByBody.TrainingPlanner.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
