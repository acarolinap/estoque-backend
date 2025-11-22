package unisul.estoquebackend.movement.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import unisul.estoquebackend.movement.domain.Movement;
import unisul.estoquebackend.movement.repository.MovementRepository;
import unisul.estoquebackend.product.exception.QuantityConflictException;
import unisul.estoquebackend.product.service.ProductMovementInterface;

@Service
public class MovementService {

	@Autowired
	private MovementRepository repository;
	
	@Autowired
	private ProductMovementInterface productMovementInterface;
	
	@Transactional
	public Movement create(Movement movement) {
		Movement saved = null;
		
		switch (movement.getType()) {
		
		case OUT:
			productMovementInterface.removeQuantity(movement.getProductId(), movement.getQuantity());
			
			saved = repository.save(movement);
			return saved;
			
		case IN:
			productMovementInterface.addQuantity(movement.getProductId(), movement.getQuantity());
			
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
