package unisul.estoquebackend.product.service;

public interface ProductMovementInterface {

	public void addQuantity(Long productId, int quantity);
	
	public void removeQuantity(Long productId, int quantity);
}
