package unisul.estoque_backend.product.mapper;

import unisul.estoque_backend.category.repository.entity.CategoryEntity;
import unisul.estoque_backend.product.domain.Product;
import unisul.estoque_backend.product.repository.entity.ProductEntity;

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
	    
	    if (domain.getCategoryId() == null) return entity;
	    
	    CategoryEntity categoryEntity = new CategoryEntity();
	    categoryEntity.setId(domain.getCategoryId());
	    
	    entity.setCategory(categoryEntity);
	    
	    return entity;
	}
}
