package unisul.estoquebackend.category.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import unisul.estoquebackend.category.repository.entity.CategoryEntity;

public interface CategoryRepositoryJpa extends JpaRepository<CategoryEntity, Long> {
}
