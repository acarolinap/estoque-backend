package unisul.estoque_backend.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import unisul.estoque_backend.category.repository.CategoryRepository; 
import unisul.estoque_backend.category.repository.entity.CategoryEntity;
import unisul.estoque_backend.category.exception.CategoryNotFoundException; 
import unisul.estoque_backend.product.domain.Product;
import unisul.estoque_backend.product.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;
	
    @Autowired
    private CategoryRepository categoryRepository; 
	
	@Transactional
	public Product create(Product product) {
        Long categoryId = product.getCategoryId();
        CategoryEntity category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));

		product = repository.save(product, category);
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
	
	@Transactional(readOnly = true)
	public List<Product> findByCategoryId(Long id){
		return repository.findByCategoryId(id);
	}

	@Transactional(readOnly = true)
	public List<Product> findByCategoryId(Long id){
		return repository.findByCategoryId(id);
	}
	
	@Transactional(readOnly = true)
	public List<Product> findByLowQuantity(){
		return repository.findByLowQuantity();
	}
	
	@Transactional
	public Product update(Product product) {
        Long categoryId = product.getCategoryId();
        CategoryEntity category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));
                
		return repository.save(product, category);
	}
	
	@Transactional
	public void delete(Long id) {
		repository.deleteById(id);
	}
}
