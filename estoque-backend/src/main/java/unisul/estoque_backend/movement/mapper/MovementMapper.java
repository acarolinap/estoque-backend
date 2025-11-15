package unisul.estoque_backend.movement.mapper;

import unisul.estoque_backend.movement.controller.representation.MovementInput;
import unisul.estoque_backend.movement.controller.representation.MovementOutput;
import unisul.estoque_backend.movement.domain.Movement;
import unisul.estoque_backend.movement.repository.entity.MovementEntity;
import unisul.estoque_backend.product.repository.entity.ProductEntity;

public class MovementMapper {

    public static Movement toDomain(MovementEntity entity) {
        Movement domain = new Movement();
        
        domain.setId(entity.getId());
        domain.setDateTime(entity.getDateTime());
        domain.setQuantity(entity.getQuantity());
        domain.setType(entity.getType());

        if (entity.getProductId() == null) return domain;

        domain.setProductId(entity.getProductId().getId());
        
        return domain;
    }

    public static MovementEntity toEntity(Movement domain) {
        MovementEntity entity = new MovementEntity();
        
        entity.setId(domain.getId());
        entity.setDateTime(domain.getDateTime());
        entity.setQuantity(domain.getQuantity());
        entity.setType(domain.getType());

        if (domain.getProductId() == null) return entity;

        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(domain.getProductId());
        entity.setProductId(productEntity);
        
        return entity;
    }
    
    public static Movement toDomain(MovementInput input) {
    	Movement domain = new Movement();
    	
    	domain.setQuantity(input.getQuantity());
    	domain.setType(input.getType());
    	domain.setProductId(input.getProductId());
    	
    	return domain;
    }
    
    public static MovementOutput toRepresentation(Movement domain) {
        MovementOutput output = new MovementOutput();

        output.setId(domain.getId());
        output.setProductId(domain.getProductId());
        output.setDateTime(domain.getDateTime().toString());
        output.setType(domain.getType().getFriendlyName());
        output.setQuantity(domain.getQuantity());

        return output;
    }
}
