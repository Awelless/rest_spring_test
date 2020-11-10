package com.example.sarafan.repo;

import com.example.sarafan.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, String> {
}
