package com.example.springsecuritydemo.rest;

import com.example.springsecuritydemo.model.Developer;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/v1")
public class DeveloperController {

    private final List<Developer> developers = Stream.of(
            new Developer(1L, "Tonya", "Ivanova"),
            new Developer(2L, "Sergei", "Sergeev"),
            new Developer(3L, "Mik", "Smitov")
    ).collect(Collectors.toList());

    @GetMapping
    public List<Developer> getAll() {
        return developers;
    }

    @GetMapping("/{id}")
    public Developer getById(@PathVariable("id") Long id) {
        return developers.stream()
                .filter(developer -> developer.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
    @PostMapping
    public Developer create(@RequestBody Developer developer){
        this.developers.add(developer);
        return developer;
    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id")Long id){
        this.developers.removeIf(developer -> developer.getId().equals(id));
    }
}
