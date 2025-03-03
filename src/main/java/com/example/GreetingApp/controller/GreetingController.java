package com.example.GreetingApp.controller;

import com.example.GreetingApp.model.Greeting;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/greeting")
public class GreetingController {

    @GetMapping
    public Greeting getGreeting() {
        return new Greeting("Hello, this is a GET request!");
    }

    @PostMapping
    public Greeting postGreeting(@RequestBody Greeting greeting) {
        return new Greeting("Received POST request with message: " + greeting.getMessage());
    }

    @PutMapping
    public Greeting putGreeting(@RequestBody Greeting greeting) {
        return new Greeting("Received PUT request with message: " + greeting.getMessage());
    }
}
