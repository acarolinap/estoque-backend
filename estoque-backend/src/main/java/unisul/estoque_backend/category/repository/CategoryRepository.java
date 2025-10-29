package unisul.estoque_backend.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import unisul.estoque_backend.category.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}