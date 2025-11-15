package unisul.estoque_backend.category.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import unisul.estoque_backend.category.domain.Category;
import unisul.estoque_backend.category.exception.CategoryNotFoundException;
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
		if (id == null || id <= 0) {
			throw new IllegalArgumentException("O ID da categoria deve ser um número positivo");
		}
		try {
			return repository.findById(id);
		} catch (CategoryNotFoundException e) {
			throw new CategoryNotFoundException(id);
		}
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
		if (category == null) {
			throw new IllegalArgumentException("A categoria não pode ser nula");
		}
		if (category.getId() == null || category.getId() <= 0) {
			throw new IllegalArgumentException("O ID da categoria é obrigatório para atualização");
		}
		
		// Valida se a categoria existe antes de atualizar
		find(category.getId());
		
		return repository.save(category);
	}
	
	@Transactional
	public boolean delete(Long id) {
		if (id == null || id <= 0) {
			throw new IllegalArgumentException("O ID da categoria deve ser um número positivo");
		}
		
		// Valida se a categoria existe antes de deletar
		find(id);
		
		repository.deleteById(id);
		return true;
	}
}
