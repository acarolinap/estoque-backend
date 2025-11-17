package unisul.estoquebackend.movement.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import unisul.estoquebackend.movement.repository.entity.MovementEntity;

public interface MovementRepositoryJpa extends JpaRepository<MovementEntity, Long> {

}
