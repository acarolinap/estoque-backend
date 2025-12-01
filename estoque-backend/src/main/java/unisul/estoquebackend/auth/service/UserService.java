package unisul.estoquebackend.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import unisul.estoquebackend.auth.domain.User;
import unisul.estoquebackend.auth.exception.UserNotFoundException;
import unisul.estoquebackend.auth.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	@Transactional(readOnly = true)
	public User findByEmail(String email) {
		return repository.findByEmail(email)
				.orElseThrow(() -> new UserNotFoundException(email));
	}
	
	@Transactional(readOnly = true)
	public User findById(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new UserNotFoundException(id));
	}
	
	@Transactional(readOnly = true)
	public boolean existsByEmail(String email) {
		return repository.existsByEmail(email);
	}
}
