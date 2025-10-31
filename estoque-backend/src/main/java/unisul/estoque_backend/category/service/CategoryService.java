package unisul.estoque_backend.category.service;

import java.util.Arrays;
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
		return repository.findById(id);
	}
	
	public List<Category.Size> getSizes(){
		List<Category.Size> list = Arrays.asList(Category.Size.values());
		return list;
	}
	
	public List<Category.Packaging> getPackaging(){
		List<Category.Packaging> list = Arrays.asList(Category.Packaging.values());
		return list;
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
