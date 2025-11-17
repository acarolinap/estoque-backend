package unisul.estoquebackend.product.repository.jpa;

import java.util.List; 

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import unisul.estoquebackend.product.repository.entity.ProductEntity;

public interface ProductRepositoryJpa extends JpaRepository<ProductEntity, Long> {
	
	public List<ProductEntity> findByCategoryId(Long categoryId);
	
	@Query("SELECT p FROM ProductEntity p WHERE p.quantity < p.minimumQuantity")
	public List<ProductEntity> findByLowQuantity();
}