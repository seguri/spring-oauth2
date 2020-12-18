package com.github.seguri.spring_oauth2.rs.repositories;

import com.github.seguri.spring_oauth2.rs.entities.HealthProfile;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HealthProfileRepository extends JpaRepository<HealthProfile, Integer> {

  Optional<HealthProfile> findHealthProfileByUsername(String username);
}
