package unisul.estoquebackend.category.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import unisul.estoquebackend.category.repository.entity.SizeEntity;
import unisul.estoquebackend.category.service.SizeService;

@RestController
@RequestMapping("/sizes")
public class SizeController {

    @Autowired
    private SizeService service;

    @GetMapping
    public ResponseEntity<List<SizeEntity>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    // O frontend envia { "name": "valor" }
    @PostMapping
    public ResponseEntity<SizeEntity> create(@RequestBody Map<String, String> payload) {
        String name = payload.get("name");
        SizeEntity saved = service.create(new SizeEntity(name));
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SizeEntity> update(@PathVariable Long id, @RequestBody Map<String, String> payload) {
        String name = payload.get("name");
        SizeEntity updated = service.update(id, name);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
