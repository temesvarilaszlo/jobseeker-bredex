package com.example.jobseeker.service;

import com.example.jobseeker.exception.RequestValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.jobseeker.repository.PositionRepository;
import com.example.jobseeker.model.Position;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PositionService {
    private final PositionRepository positionRepository;

    @Autowired
    public PositionService(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    public Position createPosition(Position position) {
        return positionRepository.save(position);
    }

    public List<Position> searchPositions(String title, String location) {
        return positionRepository.searchPositions(title, location);
    }

    public Position getPositionById(Long id) {
//        return positionRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Position not found"));
        return positionRepository.findById(id).orElseThrow(() -> {
            Map<String, String> errors = new HashMap<>();
            errors.put("position", "Nincs ilyen pozíció");
            return new RequestValidationException(errors);
        });
    }
}
