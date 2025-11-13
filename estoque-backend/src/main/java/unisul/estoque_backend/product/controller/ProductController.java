package unisul.estoque_backend.product.controller;

import java.util.List;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	public HttpEntity<Object> create(@RequestBody ProductInput input) {
		Product domain = ProductMapper.toDomain(input);
		domain = service.create(domain);
		
		ProductOutput output = ProductMapper.toRepresentation(domain);
		
		return ResponseEntity.ok(output);
	}
	
	@GetMapping
	public HttpEntity<Object> getAll(
			@RequestParam(required = false) Long categoryId,
			@RequestParam(required = false, defaultValue = "false") boolean lowQuantity
			) {
		
		// Filter by category AND low quantity
		if (categoryId != null && lowQuantity) {
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
		
		// Filter by category
		} else if (categoryId != null) {
			List<Product> list = service.findByCategoryId(categoryId);
			List<ProductOutput> output = list.stream()
					.map(ProductMapper::toRepresentation).toList();
			
			return ResponseEntity.ok(output);
			
		// Filter by low quantity
		} else if (lowQuantity) {
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
			
		// No filters - return all
		} else {
		    List<Product> found = service.findAll();
		    List<ProductOutput> output = found.stream()
		            .map(ProductMapper::toRepresentation)
		            .toList();
		    return ResponseEntity.ok(output);
		}
	}
	
	@GetMapping("/{id}")
	public HttpEntity<Object> get(@PathVariable Long id) {
		Product domain = service.find(id);
		
		ProductOutput output = ProductMapper.toRepresentation(domain);
		
		return ResponseEntity.ok(output);
	}
	
	@PutMapping
	public HttpEntity<Object> update(
			@RequestParam Long id, 
			@RequestBody ProductInput input
			) {
		
		input.setId(id);
		Product domain = ProductMapper.toDomain(input);
		
		domain = service.create(domain);
		
		ProductOutput output = ProductMapper.toRepresentation(domain);
		return ResponseEntity.ok(output);
	}
	
	@DeleteMapping
	public HttpEntity<Object> delete(@RequestParam Long id) {
		
		service.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
