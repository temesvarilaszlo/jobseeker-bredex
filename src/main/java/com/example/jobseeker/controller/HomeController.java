package com.example.jobseeker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Jobseeker API " +
                "Elérhető végpontok: " +
                "POST /client, " +
                "POST /position, " +
                "GET /position/search, " +
                "GET /position/{id}";
    }
}
