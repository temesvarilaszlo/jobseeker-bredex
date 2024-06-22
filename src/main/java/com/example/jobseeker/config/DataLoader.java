package com.example.jobseeker.config;

import com.example.jobseeker.model.Client;
import com.example.jobseeker.model.Position;
import com.example.jobseeker.repository.ClientRepository;
import com.example.jobseeker.repository.PositionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {
    @Bean
    CommandLineRunner loadData(ClientRepository clientRepository, PositionRepository positionRepository){
        return args -> {
            for (int i = 1; i <= 3; i++) {
                Client client = new Client("Client" + i, "client" + i + "@gmail.com");
                clientRepository.save(client);
            }
            for (int i = 1; i <= 3; i++) {
                Position position = new Position("Title" + i, "Location" + i);
                positionRepository.save(position);
            }
        };
    }
}