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
import unisul.estoque_backend.category.domain.Category;
import unisul.estoque_backend.category.domain.builder.CategoryBuilder;
import unisul.estoque_backend.category.service.CategoryService;

@RestController
@RequestMapping("/api/categorias")
public class CategoryController {

	@Autowired
	private CategoryService service;
	
	@PostMapping
	@ResponseBody
	public HttpEntity<Object> create(@RequestBody CategoryInput category){
		
		Category saved = service.create(new CategoryBuilder()
				.name(category.getName())
				.size(category.getSize())
				.packaging(category.getPackaging())
				.build());
		
		return ResponseEntity.ok(saved);
	}
	
	@GetMapping
	@ResponseBody
	public HttpEntity<Object> get(){
		
		List<Category> found = service.findAll();
		
		return ResponseEntity.ok(found);
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	public HttpEntity<Object> get(@PathVariable Long id){
		
		Category found = service.find(id);
		
		return ResponseEntity.ok(found);
	}
	
	@GetMapping("/tamanhos")
	public HttpEntity<Object> getSizes(){
		List<Category.Size> list = service.getSizes();
		
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/embalagens")
	public HttpEntity<Object> getPackaging(){
		List<Category.Packaging> list = service.getPackaging();
		
		return ResponseEntity.ok(list);
	}
	
	@PutMapping
	@ResponseBody
	public HttpEntity<Object> update(@RequestParam Long id, @RequestBody CategoryInput input) {
		
		Category category = new CategoryBuilder()
				.id(id)
				.name(input.getName())
				.size(input.getSize())
				.packaging(input.getPackaging())
				.build();
		
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
