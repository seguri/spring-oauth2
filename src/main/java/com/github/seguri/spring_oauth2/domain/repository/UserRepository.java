package com.github.seguri.spring_oauth2.domain.repository;

import com.github.seguri.spring_oauth2.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
  public Optional<User> findByUsername(String username);
}
