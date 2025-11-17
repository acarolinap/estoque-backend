package unisul.estoquebackend.category.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import unisul.estoquebackend.category.domain.Category;
import unisul.estoquebackend.category.exception.CategoryNotFoundException;
import unisul.estoquebackend.category.mapper.CategoryMapper;
import unisul.estoquebackend.category.repository.entity.CategoryEntity;
import unisul.estoquebackend.category.repository.jpa.CategoryRepositoryJpa;

@Repository
public class CategoryRepository {

	private final CategoryRepositoryJpa jpa;
	
	public CategoryRepository(CategoryRepositoryJpa jpa) {
		this.jpa = jpa;
	}
	
	public Category save(Category category) {
		CategoryEntity categoryEntity = CategoryMapper.toEntity(category);
		
		categoryEntity = jpa.save(categoryEntity);
		
		category = CategoryMapper.toDomain(categoryEntity);
		return category;
	}

	public List<Category> findAll() {
		List<CategoryEntity> entityList = jpa.findAll();
		
		List<Category> domainList = entityList.stream()
				.map(CategoryMapper::toDomain).toList();
		
		return domainList;
	}

	public Category findById(Long id) {
		CategoryEntity categoryEntity = jpa.findById(id)
				.orElseThrow(() -> new CategoryNotFoundException(id));
		
		Category category = CategoryMapper.toDomain(categoryEntity);
		return category;
	}

	public void deleteById(Long id) {
		jpa.deleteById(id);
	}
}