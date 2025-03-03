package com.example.GreetingApp.controller;

import com.example.GreetingApp.model.Greeting;
import com.example.GreetingApp.service.GreetingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/greeting")
public class GreetingController {

    private final GreetingService greetingService;

    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping
    public Greeting getGreeting(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName) {
        return greetingService.getGreeting(firstName, lastName);
    }

    @GetMapping("/{id}")
    public Greeting getGreetingById(@PathVariable Long id) {
        return greetingService.getGreetingById(id);
    }

    @GetMapping("/all")
    public List<Greeting> getAllGreetings() {
        return greetingService.getAllGreetings();
    }

    @PostMapping
    public Greeting saveGreeting(@RequestBody Greeting greeting) {
        return greetingService.saveGreeting(greeting);
    }

    @PutMapping("/{id}")
    public Greeting updateGreeting(@PathVariable Long id, @RequestBody Greeting greeting) {
        return greetingService.updateGreeting(id, greeting);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGreeting(@PathVariable Long id) {
        greetingService.deleteGreeting(id);
        return ResponseEntity.ok("Greeting deleted successfully!");
    }
}
