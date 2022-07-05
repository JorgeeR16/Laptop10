package com.example.obspringejercicio10.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.obspringejercicio10.Interface.LaptopRepository;
import com.example.obspringejercicio10.Model.Laptop;

@RestController
public class LaptopController {

    private LaptopRepository laptopRepository;

    public LaptopController(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }

    @GetMapping("/api/laptops")
    public ResponseEntity<List<Laptop>> findAll() {
        return ResponseEntity.ok(laptopRepository.findAll());
    }

    @GetMapping("/api/laptops/{id}")
    public ResponseEntity<Laptop> findAll(@PathVariable Long id) {
        Optional<Laptop> repository = laptopRepository.findById(id);
        if (repository.isPresent()) {
            return ResponseEntity.ok(repository.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/api/laptops")
    public ResponseEntity<Laptop> create(@RequestBody Laptop laptop) {
        if (laptop.getId() != null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(laptopRepository.save(laptop));
    }

    @PutMapping("/api/laptops")
    public ResponseEntity<Laptop> update(@RequestBody Laptop laptop) {
        if (laptop.getId() == null) {
            return ResponseEntity.badRequest().build();
        }
        if (laptopRepository.existsById(laptop.getId())) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(laptopRepository.save(laptop));
    }

    @DeleteMapping("api/laptops/{id}")
    public ResponseEntity<Laptop> delete(@PathVariable Long id) {
        if (!laptopRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        laptopRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/api/laptops")
    public ResponseEntity<Laptop> deleteAll(){
        laptopRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
