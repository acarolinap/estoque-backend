package unisul.estoquebackend.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import unisul.estoquebackend.auth.controller.representation.AuthResponse;
import unisul.estoquebackend.auth.controller.representation.UserOutput;
import unisul.estoquebackend.auth.domain.User;
import unisul.estoquebackend.auth.exception.InvalidCredentialsException;
import unisul.estoquebackend.auth.exception.UserAlreadyExistsException;
import unisul.estoquebackend.auth.repository.UserRepository;

@Service
public class AuthService {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Transactional
	public AuthResponse register(String email, String password) {
		// Verifica se usuário já existe
		if (repository.existsByEmail(email)) {
			throw new UserAlreadyExistsException(email);
		}
		
		// Cria novo usuário
		User user = new User();
		user.setEmail(email);
		user.setPassword(passwordEncoder.encode(password));
		
		User saved = repository.save(user);
		
		// Gera token
		String token = jwtService.generateToken(saved.getEmail());
		
		// Retorna resposta
		UserOutput userOutput = new UserOutput(saved.getId(), saved.getEmail());
		return new AuthResponse(token, userOutput);
	}
	
	@Transactional(readOnly = true)
	public AuthResponse login(String email, String password) {
		// Busca usuário
		User user = repository.findByEmail(email)
				.orElseThrow(() -> new InvalidCredentialsException());
		
		// Verifica senha
		if (!passwordEncoder.matches(password, user.getPassword())) {
			throw new InvalidCredentialsException();
		}
		
		// Gera token
		String token = jwtService.generateToken(user.getEmail());
		
		// Retorna resposta
		UserOutput userOutput = new UserOutput(user.getId(), user.getEmail());
		return new AuthResponse(token, userOutput);
	}
}
