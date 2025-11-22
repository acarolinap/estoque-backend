package unisul.estoquebackend.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import unisul.estoquebackend.category.exception.CategoryNotFoundException;
import unisul.estoquebackend.category.service.CategoryService;
import unisul.estoquebackend.product.domain.Product;
import unisul.estoquebackend.product.exception.ProductNotFoundException;
import unisul.estoquebackend.product.repository.ProductRepository;

@Service
public class ProductService implements ProductMovementInterface {

	@Autowired
	private ProductRepository repository;
	
	@Autowired
	private CategoryService categoryService;
	
	@Transactional
	public Product create(Product product) {
		if (product == null) {
			throw new IllegalArgumentException("O produto não pode ser nulo");
		}
		
		// Valida se a categoria existe antes de criar o produto
		if (product.getCategoryId() != null) {
			try {
				categoryService.find(product.getCategoryId());
			} catch (CategoryNotFoundException e) {
				throw new CategoryNotFoundException(product.getCategoryId());
			}
		}
		
		product = repository.save(product);
		return product;
	}
	
	@Transactional(readOnly = true)
	public List<Product> findAll() {
		return repository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Product find(Long id) {
		if (id == null || id <= 0) {
			throw new IllegalArgumentException("O ID do produto deve ser um número positivo");
		}
		try {
			return repository.findById(id);
		} catch (ProductNotFoundException e) {
			throw new ProductNotFoundException(id);
		}
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
		if (product == null) {
			throw new IllegalArgumentException("O produto não pode ser nulo");
		}
		if (product.getId() == null || product.getId() <= 0) {
			throw new IllegalArgumentException("O ID do produto é obrigatório para atualização");
		}
		
		// Valida se o produto existe antes de atualizar
		find(product.getId());
		
		// Valida se a categoria existe antes de atualizar o produto
		if (product.getCategoryId() != null) {
			try {
				categoryService.find(product.getCategoryId());
			} catch (CategoryNotFoundException e) {
				throw new CategoryNotFoundException(product.getCategoryId());
			}
		}
		
		return repository.save(product);
	}
	
	@Transactional
	public void delete(Long id) {
		if (id == null || id <= 0) {
			throw new IllegalArgumentException("O ID do produto deve ser um número positivo");
		}
		
		// Valida se o produto existe antes de deletar
		find(id);
		
		repository.deleteById(id);
	}

	@Override
	@Transactional
	public void addQuantity(Long productId, int quantity) {
		Product product = repository.findById(productId);
		
		product.addQuantity(quantity);
		repository.save(product);
	}

	@Override
	@Transactional
	public void removeQuantity(Long productId, int quantity) {
		Product product = repository.findById(productId);
		
		product.removeQuantity(quantity);
		repository.save(product);
	}
}
