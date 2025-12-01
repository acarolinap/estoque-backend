package unisul.estoquebackend.category.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import unisul.estoquebackend.category.repository.entity.PackagingEntity;
import unisul.estoquebackend.category.service.PackagingService;

@RestController
@RequestMapping("/packagings")
public class PackagingController {

    @Autowired
    private PackagingService service;

    @GetMapping
    public ResponseEntity<List<PackagingEntity>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    public ResponseEntity<PackagingEntity> create(@RequestBody Map<String, String> payload) {
        String name = payload.get("name");
        PackagingEntity saved = service.create(new PackagingEntity(name));
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PackagingEntity> update(@PathVariable Long id, @RequestBody Map<String, String> payload) {
        String name = payload.get("name");
        PackagingEntity updated = service.update(id, name);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
