package unisul.estoque_backend.category.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import unisul.estoque_backend.category.domain.Category;
import unisul.estoque_backend.category.repository.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository repository;

	@Transactional
	public Category create(Category category) {
		return repository.save(category);
	}
	
	@Transactional(readOnly = true)
	public List<Category> findAll(){
		return repository.findAll();
	}

	@Transactional(readOnly = true)
	public Category find(Long id) {
		return repository.findById(id).orElse(null);
	}
	
	@Transactional
	public Category update(Category category) {
		return repository.save(category);
	}
	
	@Transactional
	public boolean delete(Long id) {
		repository.deleteById(id);
		return true;
	}
}
