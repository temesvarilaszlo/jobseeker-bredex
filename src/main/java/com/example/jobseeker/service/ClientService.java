package com.example.jobseeker.service;

import com.example.jobseeker.exception.RequestValidationException;
import com.example.jobseeker.model.Client;
import com.example.jobseeker.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    public Client registerClient(Client client) {
        if (clientRepository.existsByEmail(client.getEmail())) {
            Map<String, String> errors = new HashMap<>();
            errors.put("email", "Ilyen emaillel már létezik kliens.");
            throw new RequestValidationException(errors);
        }
        return clientRepository.save(client);
    }

    public Client findByApiKey(String apiKeyStr) {
        try {
            UUID apiKey = convertStringToUUID(apiKeyStr);
            return clientRepository.findByApiKey(apiKey)
                    .orElseThrow(() -> {
                        Map<String, String> errors = new HashMap<>();
                        errors.put("apiKey", "Helytelen API kulcs!");
                        return new RequestValidationException(errors);
                    });
        }
        catch (IllegalArgumentException e){
            Map<String, String> errors = new HashMap<>();
            errors.put("apiKey", "Helytelen API kulcs!");
            throw new RequestValidationException(errors);
        }
    }

    private UUID convertStringToUUID(String uuidString) {
        return UUID.fromString(uuidString.replaceAll(
                "([a-fA-F0-9]{8})([a-fA-F0-9]{4})([a-fA-F0-9]{4})([a-fA-F0-9]{4})([a-fA-F0-9]{12})",
                "$1-$2-$3-$4-$5"
        ));
    }
}
