package unisul.estoque_backend.category.mapper;

import unisul.estoque_backend.category.controller.representation.CategoryRepresentation;
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
	
	public static Category toDomain(CategoryRepresentation representation) {
		Category domain = new Category();
		
		domain.setId(representation.getId());
		domain.setName(representation.getName());
		domain.setSize(representation.getSize());
		domain.setPackaging(representation.getPackaging());
		
		return domain;
	}
	
	public static CategoryRepresentation toRepresentation(Category domain) {
		CategoryRepresentation representation = new CategoryRepresentation();
		
		representation.setId(domain.getId());
		representation.setName(domain.getName());
		representation.setSize(domain.getSize());
		representation.setPackaging(domain.getPackaging());
		
		return representation;
	}
}
