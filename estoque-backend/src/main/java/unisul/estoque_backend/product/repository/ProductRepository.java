package unisul.estoque_backend.product.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import unisul.estoque_backend.product.domain.Product;
import unisul.estoque_backend.product.exception.ProductNotFoundException;
import unisul.estoque_backend.product.mapper.ProductMapper;
import unisul.estoque_backend.product.repository.entity.ProductEntity;
import unisul.estoque_backend.product.repository.jpa.ProductRepositoryJpa;
import unisul.estoque_backend.category.repository.entity.CategoryEntity;

@Repository
public class ProductRepository {

	private final ProductRepositoryJpa jpa;
	
	public ProductRepository(ProductRepositoryJpa jpa) {
		this.jpa = jpa;
	}
	
	public Product save(Product product, CategoryEntity category) {
		ProductEntity entity = ProductMapper.toEntity(product, category);
		entity = jpa.save(entity);
		
		product = ProductMapper.toDomain(entity);
		
		return product;
	}
	
	public Product save(Product product) {
		ProductEntity entity = ProductMapper.toEntity(product);
		entity = jpa.save(entity);
		
		product = ProductMapper.toDomain(entity);
		
		return product;
	}
	
	public List<Product> findAll(){
		List<ProductEntity> found = jpa.findAll();
		
		List<Product> list = found.stream()
				.map(ProductMapper::toDomain).toList();
		
		return list;
	}
	
	public Product findById(Long id) {
		ProductEntity found = jpa.findById(id)
				.orElseThrow(() -> new ProductNotFoundException(id));
		
		Product domain = ProductMapper.toDomain(found);
		
		return domain;
	}
	
	public void deleteById(Long id) {
		
		jpa.deleteById(id);

	public List<Product> findByCategoryId(Long id) {
		List<ProductEntity> found = jpa.findByCategoryId(id);
		List<Product> list = found.stream()
				.map(ProductMapper::toDomain).toList();
		
		return list;
	}
	
		public List<Product> findByLowQuantity(){
		List<ProductEntity> found = jpa.findByLowQuantity();
		List<Product> list = found.stream()
				.map(ProductMapper::toDomain).toList();
		
		return list;
	}
}
