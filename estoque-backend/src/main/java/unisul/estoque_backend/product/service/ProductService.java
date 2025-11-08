package unisul.estoque_backend.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import unisul.estoque_backend.product.domain.Product;
import unisul.estoque_backend.product.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;
	
	@Transactional
	public Product create(Product product) {
		product = repository.save(product);
		return product;
	}
	
	@Transactional(readOnly = true)
	public List<Product> findAll() {
		return repository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Product find(Long id) {
		return repository.findById(id);
	}
	
	@Transactional
	public Product update(Product product) {
		return repository.save(product);
	}
	
	@Transactional
	public void delete(Long id) {
		repository.deleteById(id);
	}
}
