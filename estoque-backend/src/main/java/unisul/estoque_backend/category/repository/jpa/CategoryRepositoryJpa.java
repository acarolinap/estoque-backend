package unisul.estoque_backend.category.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import unisul.estoque_backend.category.repository.entity.CategoryEntity;

public interface CategoryRepositoryJpa extends JpaRepository<CategoryEntity, Long> {
}
