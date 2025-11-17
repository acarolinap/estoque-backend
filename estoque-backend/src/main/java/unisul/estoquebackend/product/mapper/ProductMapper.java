package unisul.estoquebackend.product.mapper;

import unisul.estoquebackend.category.repository.entity.CategoryEntity;
import unisul.estoquebackend.product.controller.representation.ProductInput;
import unisul.estoquebackend.product.controller.representation.ProductOutput;
import unisul.estoquebackend.product.domain.Product;
import unisul.estoquebackend.product.repository.entity.ProductEntity;

public class ProductMapper {

	public static Product toDomain(ProductEntity entity) {
		Product domain = new Product();
		
		domain.setId(entity.getId());
		domain.setName(entity.getName());
		domain.setQuantity(entity.getQuantity());
		domain.setMinimumQuantity(entity.getMinimumQuantity());
		domain.setMaximumQuantity(entity.getMaximumQuantity());
		domain.setPrice(entity.getPrice());
		domain.setUnit(entity.getUnit());
		domain.setCategoryId(entity.getCategory().getId());
		
		return domain;
	}
	
	public static ProductEntity toEntity(Product domain) {
		ProductEntity entity = new ProductEntity();
		
		entity.setId(domain.getId());
	    entity.setName(domain.getName());
	    entity.setQuantity(domain.getQuantity());
	    entity.setMinimumQuantity(domain.getMinimumQuantity());
	    entity.setMaximumQuantity(domain.getMaximumQuantity());
	    entity.setPrice(domain.getPrice());
	    entity.setUnit(domain.getUnit());
	    
	    CategoryEntity categoryEntity = new CategoryEntity();
	    categoryEntity.setId(domain.getCategoryId());
	    
	    entity.setCategory(categoryEntity);
	    
	    return entity;
	}
	
	// Representation to Domain
	public static Product toDomain(ProductInput input) {
		Product domain = new Product();
		
		domain.setId(input.getId());
		domain.setName(input.getName());
		domain.setQuantity(input.getQuantity());
		domain.setMinimumQuantity(input.getMinimumQuantity());
		domain.setMaximumQuantity(input.getMaximumQuantity());
		domain.setPrice(input.getPrice());
		domain.setUnit(input.getUnit());
		domain.setCategoryId(input.getCategoryId());
		
		return domain;
	}
	
	// Domain to Representation
	public static ProductOutput toRepresentation(Product domain) {
		ProductOutput output = new ProductOutput();
		
		output.setId(domain.getId());
		output.setName(domain.getName());
		output.setQuantity(domain.getQuantity());
		output.setMinimumQuantity(domain.getMinimumQuantity());
		output.setMaximumQuantity(domain.getMaximumQuantity());
		output.setPrice(domain.getPrice());
		output.setUnit(domain.getUnit());
		output.setCategoryId(domain.getCategoryId());
		
		return output;
	}
}
