package com.arrowapp.api.v1.repositories;

import com.arrowapp.api.v1.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository

public interface UserRepository extends JpaRepository<User, Integer> {
}
