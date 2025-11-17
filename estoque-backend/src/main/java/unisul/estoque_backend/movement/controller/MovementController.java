package unisul.estoque_backend.movement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import unisul.estoque_backend.movement.controller.representation.MovementEnumRepresentation;
import unisul.estoque_backend.movement.controller.representation.MovementInput;
import unisul.estoque_backend.movement.controller.representation.MovementOutput;
import unisul.estoque_backend.movement.domain.Movement;
import unisul.estoque_backend.movement.mapper.MovementEnumMapper;
import unisul.estoque_backend.movement.mapper.MovementMapper;
import unisul.estoque_backend.movement.service.MovementService;

@RestController
@RequestMapping("/api/movimentacoes")
public class MovementController {
	
	@Autowired
	private MovementService service;

	@PostMapping
	public HttpEntity<Object> create(@RequestBody MovementInput input) {
		Movement domain = MovementMapper.toDomain(input);
		Movement saved = service.create(domain);
		
		return ResponseEntity.ok(saved);
	}
	
	@GetMapping
	public HttpEntity<Object> get(){
		List<Movement> found = service.findAll();
		
		List<MovementOutput> output = found.stream()
				.map(MovementMapper::toRepresentation).toList();
		
		return ResponseEntity.ok(output);
	}
	
	@GetMapping("/{id}")
	public HttpEntity<Object> get(@PathVariable Long id) {
		Movement found = service.find(id);
		MovementOutput output = MovementMapper.toRepresentation(found);
		
		return ResponseEntity.ok(output);
	}
	
	@GetMapping("/tipos")
	public HttpEntity<Object> getTypes(){
		List<Movement.Type> list = service.getTypes();
		List<MovementEnumRepresentation> output = list.stream()
				.map(MovementEnumMapper::toRepresentation).toList();
		
		return ResponseEntity.ok(output);
	}
}
