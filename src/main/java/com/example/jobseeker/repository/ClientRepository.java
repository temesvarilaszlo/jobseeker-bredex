package com.example.jobseeker.repository;
import com.example.jobseeker.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByApiKey(UUID apiKey);
    boolean existsByEmail(String email);
}
