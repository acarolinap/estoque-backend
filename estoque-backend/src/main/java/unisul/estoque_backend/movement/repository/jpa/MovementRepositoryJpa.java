package unisul.estoque_backend.movement.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import unisul.estoque_backend.movement.repository.entity.MovementEntity;

public interface MovementRepositoryJpa extends JpaRepository<MovementEntity, Long> {

}
