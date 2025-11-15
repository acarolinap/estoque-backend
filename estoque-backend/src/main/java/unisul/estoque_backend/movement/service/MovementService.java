package unisul.estoque_backend.movement.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import unisul.estoque_backend.movement.domain.Movement;
import unisul.estoque_backend.movement.repository.MovementRepository;

@Service
public class MovementService {

	@Autowired
	private MovementRepository repository;
	
	@Transactional
	public Movement create(Movement movement) {
		Movement saved = repository.save(movement);
		
		return saved;
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
