package unisul.estoque_backend.category.mapper;

import unisul.estoque_backend.category.controller.representation.CategoryInput;
import unisul.estoque_backend.category.controller.representation.CategoryOutput;
import unisul.estoque_backend.category.domain.Category;
import unisul.estoque_backend.category.repository.entity.CategoryEntity;

public class CategoryMapper {

	// Domain to Entity
	public static CategoryEntity toEntity(Category domain) {
		CategoryEntity entity = new CategoryEntity();
		
		entity.setId(domain.getId());
		entity.setName(domain.getName());
		entity.setSize(domain.getSize());
		entity.setPackaging(domain.getPackaging());
		
		return entity;
	}
	
	// Entity to Domain
	public static Category toDomain(CategoryEntity entity) {
		Category domain = new Category();
		
		domain.setId(entity.getId());
		domain.setName(entity.getName());
		domain.setSize(entity.getSize());
		domain.setPackaging(entity.getPackaging());
		
		return domain;
	}
	
	// Representation to Domain
	public static Category toDomain(CategoryInput input) {
		Category domain = new Category();
		
		domain.setId(input.getId());
		domain.setName(input.getName());
		domain.setSize(input.getSize());
		domain.setPackaging(input.getPackaging());
		
		return domain;
	}
	
	// Domain to Representation
	public static CategoryOutput toRepresentation(Category domain) {
		CategoryOutput output = new CategoryOutput();
		
		output.setId(domain.getId());
		output.setName(domain.getName());
		output.setSize(
				CategoryEnumMapper.toRepresentation( domain.getSize() )
				);
		output.setPackaging(
				CategoryEnumMapper.toRepresentation(domain.getPackaging())
				);
		
		return output;
	}
}
