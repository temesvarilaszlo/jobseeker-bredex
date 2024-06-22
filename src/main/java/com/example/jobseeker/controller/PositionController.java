package com.example.jobseeker.controller;

import com.example.jobseeker.exception.RequestValidationException;
import com.example.jobseeker.model.Client;
import com.example.jobseeker.model.Position;
import com.example.jobseeker.service.ClientService;
import com.example.jobseeker.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/position")
public class PositionController {
    private final PositionService positionService;

    private final ClientService clientService;

    @Autowired
    public PositionController(PositionService positionService, ClientService clientService) {
        this.positionService = positionService;
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<String> createPosition(@RequestHeader("apiKey") String apiKeyStr,
                                                 @Valid @RequestBody PositionRequest positionRequest)
    {
        Client client = clientService.findByApiKey(apiKeyStr);
        Position position = new Position(positionRequest.getTitle(), positionRequest.getLocation());

        Position savedPosition = positionService.createPosition(position);
        return ResponseEntity.ok("/position/" + savedPosition.getId());
    }

    @GetMapping("/search")
    public ResponseEntity<List<String>> searchPositions(@RequestHeader("apiKey") String apiKeyStr,
                                                        @RequestParam String title,
                                                        @RequestParam String location)
    {
        Client client = clientService.findByApiKey(apiKeyStr);
        Map<String, String> errors = new HashMap<>();
        if (title.length() > 50) {
            errors.put("title", "A pozíció neve max 50 karakter lehet!");
        }
        if (location.length() > 50) {
            errors.put("location", "A hely neve max 50 karakter lehet!");
        }
        if (!errors.isEmpty()){
            throw new RequestValidationException(errors);
        }

        List<Position> positions = positionService.searchPositions(title, location);
        List<String> urls = positions.stream().map(position -> "/position/" + position.getId()).collect(Collectors.toList());
        return ResponseEntity.ok(urls);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Position> getPositionById(@PathVariable Long id){
        Position position = positionService.getPositionById(id);
        return ResponseEntity.ok(position);
    }

    public static class PositionRequest {
        @Size(max = 50)
        private String title;

        @Size(max = 50)
        private String location;

        public String getTitle() {
            return title;
        }

        public String getLocation() {
            return location;
        }
    }
}
