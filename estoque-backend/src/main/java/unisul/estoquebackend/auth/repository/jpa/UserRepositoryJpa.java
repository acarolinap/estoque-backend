package unisul.estoquebackend.auth.repository.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import unisul.estoquebackend.auth.repository.entity.UserEntity;

public interface UserRepositoryJpa extends JpaRepository<UserEntity, Long> {
	
	Optional<UserEntity> findByEmail(String email);
	
	boolean existsByEmail(String email);
}
