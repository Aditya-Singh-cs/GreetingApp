package com.example.GreetingApp.service;

import com.example.GreetingApp.model.Greeting;
import com.example.GreetingApp.repository.GreetingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GreetingService {

    private final GreetingRepository greetingRepository;

    public GreetingService(GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

    public Greeting getGreeting(String firstName, String lastName) {
        String message;
        if ((firstName == null || firstName.isBlank()) && (lastName == null || lastName.isBlank())) {
            message = "Hello World";
        } else {
            String fullName = (firstName != null ? firstName : "") +
                    (lastName != null ? " " + lastName : "");
            message = "Hello " + fullName.trim();
        }

        Greeting greeting = new Greeting(message);
        return greetingRepository.save(greeting);
    }

    public List<Greeting> getAllGreetings() {
        return greetingRepository.findAll();
    }
}
