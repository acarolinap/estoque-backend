package unisul.estoque_backend.product.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import unisul.estoque_backend.product.repository.entity.ProductEntity;

public interface ProductRepositoryJpa extends JpaRepository<ProductEntity, Long> {
}
