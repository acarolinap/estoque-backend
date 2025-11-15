package unisul.estoque_backend.movement.controller.representation;

import unisul.estoque_backend.movement.domain.Movement;

public class MovementInput {

	private Integer quantity;
	private Movement.Type type;
	private Long productId;
	
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Movement.Type getType() {
		return type;
	}
	public void setType(Movement.Type type) {
		this.type = type;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
}
