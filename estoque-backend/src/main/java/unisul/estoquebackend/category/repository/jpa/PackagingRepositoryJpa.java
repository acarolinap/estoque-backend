package unisul.estoquebackend.category.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import unisul.estoquebackend.category.repository.entity.PackagingEntity;

public interface PackagingRepositoryJpa extends JpaRepository<PackagingEntity, Long> {
}
