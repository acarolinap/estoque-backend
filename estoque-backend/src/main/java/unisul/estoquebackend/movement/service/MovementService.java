package unisul.estoquebackend.movement.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import unisul.estoquebackend.movement.domain.Movement;
import unisul.estoquebackend.movement.exception.StockConflictException;
import unisul.estoquebackend.movement.repository.MovementRepository;
import unisul.estoquebackend.product.service.ProductService;

@Service
public class MovementService {

	@Autowired
	private MovementRepository repository;
	
	@Autowired
	private ProductService productService;
	
	@Transactional
	public Movement create(Movement movement) {
		Movement saved = null;
		
		switch (movement.getType()) {
		
		case OUT:
			Long productId = movement.getProductId();
			Integer transactionQuantity = movement.getQuantity();
			Integer productQuantity = productService.find(productId).getQuantity();
			
			if (transactionQuantity > productQuantity) throw new StockConflictException("Could not complete movement: Not enough quantity in stock"); // This should return HTTP 409
			
			saved = repository.save(movement);
			return saved;
			
		case IN:
			saved = repository.save(movement);
			return saved;
			
		default:
			throw new IllegalArgumentException("Unexpected movement type in MovementService.create");
		}
	}
	
	@Transactional(readOnly = true)
	public List<Movement> findAll() {
		return repository.findAll();
	}

	@Transactional(readOnly = true)
	public Movement find(Long id) {
		return repository.findById(id);
	}

	@Transactional(readOnly = true)
	public List<Movement.Type> getTypes() {
		List<Movement.Type> list = Arrays.asList(Movement.Type.values());
		
		return list;
	}
}
