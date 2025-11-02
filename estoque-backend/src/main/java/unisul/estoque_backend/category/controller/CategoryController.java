package unisul.estoque_backend.category.controller;

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

import unisul.estoque_backend.category.controller.representation.CategoryInput;
import unisul.estoque_backend.category.controller.representation.CategoryOutput;
import unisul.estoque_backend.category.controller.representation.EnumRepresentation;
import unisul.estoque_backend.category.domain.Category;
import unisul.estoque_backend.category.domain.builder.CategoryBuilder;
import unisul.estoque_backend.category.mapper.CategoryEnumMapper;
import unisul.estoque_backend.category.mapper.CategoryMapper;
import unisul.estoque_backend.category.service.CategoryService;

@RestController
@RequestMapping("/api/categorias")
public class CategoryController {

	@Autowired
	private CategoryService service;
	
	@PostMapping
	@ResponseBody
	public HttpEntity<Object> create(@RequestBody CategoryInput input){
		
		Category domain = CategoryMapper.toDomain(input);
		Category saved = service.create(domain);
		CategoryOutput output = CategoryMapper.toRepresentation(saved);
		
		return ResponseEntity.ok(output);
	}
	
	@GetMapping
	@ResponseBody
	public HttpEntity<Object> get(){
		
		List<Category> found = service.findAll();
		
		List<CategoryOutput> output = found.stream()
				.map(CategoryMapper::toRepresentation).toList();
		
		return ResponseEntity.ok(output);
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	public HttpEntity<Object> get(@PathVariable Long id){
		
		Category found = service.find(id);
		CategoryOutput output = CategoryMapper.toRepresentation(found);
		
		return ResponseEntity.ok(output);
	}
	
	@GetMapping("/tamanhos")
	public HttpEntity<Object> getSizes(){
		List<Category.Size> list = service.getSizes();
		List<EnumRepresentation> output = list.stream()
				.map(CategoryEnumMapper::toRepresentation).toList();
		
		return ResponseEntity.ok(output);
	}
	
	@GetMapping("/embalagens")
	public HttpEntity<Object> getPackaging(){
		List<Category.Packaging> list = service.getPackaging();
		List<EnumRepresentation> output = list.stream()
				.map(CategoryEnumMapper::toRepresentation).toList();
		
		return ResponseEntity.ok(output);
	}
	
	@PutMapping
	@ResponseBody
	public HttpEntity<Object> update(@RequestParam Long id, @RequestBody CategoryInput input) {
		
		input.setId(id);
		
		Category category = CategoryMapper.toDomain(input);
		
		Category updated = service.update(category);
		
		return ResponseEntity.ok(updated);
	}
	
	@DeleteMapping
	public HttpEntity<Object> delete(@RequestParam Long id) {
		if (service.delete(id)) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
