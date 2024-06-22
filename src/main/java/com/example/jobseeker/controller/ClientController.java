package com.example.jobseeker.controller;

import com.example.jobseeker.model.Client;
import com.example.jobseeker.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.Pattern;


@RestController
@RequestMapping("/client")
public class ClientController {
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<UUID> registerClient(@Valid @RequestBody ClientRequest clientRequest){
        Client newClient = new Client(clientRequest.getName(), clientRequest.getEmail());
        Client savedClient = clientService.registerClient(newClient);
        return ResponseEntity.ok(savedClient.getApiKey());
    }

    public static class ClientRequest {
        @Size(max = 100)
        @NotBlank
        private String name;

        @Size(max = 100)
        @NotBlank
        @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", message = "Helytelen email formátum.")
        private String email;

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }
    }
}