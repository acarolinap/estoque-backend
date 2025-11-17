package unisul.estoquebackend.product.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import unisul.estoquebackend.product.controller.representation.ProductInput;
import unisul.estoquebackend.product.controller.representation.ProductOutput;
import unisul.estoquebackend.product.domain.Product;
import unisul.estoquebackend.product.mapper.ProductMapper;
import unisul.estoquebackend.product.service.ProductService;

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
			
		// Filter by low quantity (AQUI ESTÁ A MUDANÇA!)
		} else if (lowQuantity) {
			List<Product> list = service.findByLowQuantity();
			List<ProductOutput> output = list.stream()
					.map(ProductMapper::toRepresentation).toList();
			
			return ResponseEntity.ok(output);
			
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

