package unisul.estoquebackend.auth.repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import unisul.estoquebackend.auth.domain.User;
import unisul.estoquebackend.auth.mapper.UserMapper;
import unisul.estoquebackend.auth.repository.entity.UserEntity;
import unisul.estoquebackend.auth.repository.jpa.UserRepositoryJpa;

@Repository
public class UserRepository {
	
	@Autowired
	private UserRepositoryJpa jpa;
	
	public User save(User user) {
		UserEntity entity = UserMapper.toEntity(user);
		UserEntity saved = jpa.save(entity);
		return UserMapper.toDomain(saved);
	}
	
	public Optional<User> findByEmail(String email) {
		return jpa.findByEmail(email)
				.map(UserMapper::toDomain);
	}
	
	public Optional<User> findById(Long id) {
		return jpa.findById(id)
				.map(UserMapper::toDomain);
	}
	
	public boolean existsByEmail(String email) {
		return jpa.existsByEmail(email);
	}
}
