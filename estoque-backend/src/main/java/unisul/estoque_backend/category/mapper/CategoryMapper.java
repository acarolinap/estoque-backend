package unisul.estoque_backend.category.mapper;

import unisul.estoque_backend.category.domain.Category;
import unisul.estoque_backend.category.repository.entity.CategoryEntity;

public class CategoryMapper {

	public static CategoryEntity toEntity(Category domain) {
		CategoryEntity entity = new CategoryEntity();
		
		entity.setId(domain.getId());
		entity.setName(domain.getName());
		entity.setSize(domain.getSize());
		entity.setPackaging(domain.getPackaging());
		
		return entity;
	}
	
	public static Category toDomain(CategoryEntity entity) {
		Category domain = new Category();
		
		domain.setId(entity.getId());
		domain.setName(entity.getName());
		domain.setSize(entity.getSize());
		domain.setPackaging(entity.getPackaging());
		
		return domain;
	}
}
