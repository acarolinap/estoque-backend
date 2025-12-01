package unisul.estoquebackend.category.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import unisul.estoquebackend.category.repository.entity.SizeEntity;

public interface SizeRepositoryJpa extends JpaRepository<SizeEntity, Long> {
}
