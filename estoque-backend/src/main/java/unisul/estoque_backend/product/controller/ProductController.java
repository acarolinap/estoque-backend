package unisul.estoque_backend.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import unisul.estoque_backend.product.controller.representation.ProductInput;
import unisul.estoque_backend.product.controller.representation.ProductOutput;
import unisul.estoque_backend.product.domain.Product;
import unisul.estoque_backend.product.mapper.ProductMapper;
import unisul.estoque_backend.product.service.ProductService;

@RestController
@RequestMapping("/api/produtos")
public class ProductController {

	@Autowired
	private ProductService service;
	
	@PostMapping
	@ResponseBody
	public HttpEntity<Object> create(@Valid @RequestBody ProductInput input) {
		Product domain = ProductMapper.toDomain(input);
		Product saved = service.create(domain);
		ProductOutput output = ProductMapper.toRepresentation(saved);
		
		return ResponseEntity.ok(output);
	}
	
	@GetMapping
	@ResponseBody
	public HttpEntity<Object> get() {
		List<Product> found = service.findAll();
		List<ProductOutput> output = found.stream()
				.map(ProductMapper::toRepresentation).toList();
		
		return ResponseEntity.ok(output);
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	public HttpEntity<Object> get(@PathVariable @NotNull(message = "O ID é obrigatório") @Positive(message = "O ID deve ser um número positivo") Long id) {
		Product found = service.find(id);
		ProductOutput output = ProductMapper.toRepresentation(found);
		
		return ResponseEntity.ok(output);
	}
	
	@PutMapping
	@ResponseBody
	public HttpEntity<Object> update(@RequestParam @NotNull(message = "O ID é obrigatório") @Positive(message = "O ID deve ser um número positivo") Long id, @Valid @RequestBody ProductInput input) {
		input.setId(id);
		
		Product product = ProductMapper.toDomain(input);
		Product updated = service.update(product);
		ProductOutput output = ProductMapper.toRepresentation(updated);
		
		return ResponseEntity.ok(output);
	}
	
	@DeleteMapping
	public HttpEntity<Object> delete(@RequestParam @NotNull(message = "O ID é obrigatório") @Positive(message = "O ID deve ser um número positivo") Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}

