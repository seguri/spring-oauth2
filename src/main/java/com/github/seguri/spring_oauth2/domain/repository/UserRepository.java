package com.github.seguri.spring_oauth2.domain.repository;

import com.github.seguri.spring_oauth2.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {}
