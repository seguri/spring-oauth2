package com.github.seguri.spring_oauth2.domain.repository;

import com.github.seguri.spring_oauth2.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {}
