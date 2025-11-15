package unisul.estoque_backend.movement.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import unisul.estoque_backend.movement.domain.Movement;
import unisul.estoque_backend.movement.exception.MovementNotFoundException;
import unisul.estoque_backend.movement.mapper.MovementMapper;
import unisul.estoque_backend.movement.repository.entity.MovementEntity;
import unisul.estoque_backend.movement.repository.jpa.MovementRepositoryJpa;

@Repository
public class MovementRepository {

	private final MovementRepositoryJpa jpa;
	
	public MovementRepository(MovementRepositoryJpa jpa) {
		this.jpa = jpa;
	}
		
	public Movement save(Movement movement) {
		MovementEntity entity = MovementMapper.toEntity(movement);
		MovementEntity saved = jpa.save(entity);
		Movement domain = MovementMapper.toDomain(saved);
		
		return domain;
	}
	
	public List<Movement> findAll() {
		List<MovementEntity> found = jpa.findAll();
		List<Movement> domain = found.stream()
				.map(MovementMapper::toDomain).toList();
		
		return domain;
	}
	
	public Movement findById(Long id) {
		MovementEntity found = jpa.findById(id)
				.orElseThrow(() -> new MovementNotFoundException(id));
		Movement domain = MovementMapper.toDomain(found);
		
		return domain;
	}
	
	public void deleteById(Long id) {
		jpa.deleteById(id);
	}
}
