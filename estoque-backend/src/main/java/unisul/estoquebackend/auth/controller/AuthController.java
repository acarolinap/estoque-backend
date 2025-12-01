package unisul.estoquebackend.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import unisul.estoquebackend.auth.controller.representation.AuthResponse;
import unisul.estoquebackend.auth.controller.representation.LoginRequest;
import unisul.estoquebackend.auth.controller.representation.RegisterRequest;
import unisul.estoquebackend.auth.controller.representation.UserOutput;
import unisul.estoquebackend.auth.domain.User;
import unisul.estoquebackend.auth.service.AuthService;
import unisul.estoquebackend.auth.service.UserService;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = {"http://localhost:5173", "https://a3-sistemas-distribuidos-deploy-fro.vercel.app"}, allowCredentials = "true")
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public HttpEntity<Object> register(@Valid @RequestBody RegisterRequest request) {
		try {
			AuthResponse response = authService.register(request.getEmail(), request.getPassword());
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			System.err.println("Erro ao registrar usuário: " + e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(500).body("Erro interno do servidor");
		}
	}
	
	@PostMapping("/login")
	public HttpEntity<Object> login(@Valid @RequestBody LoginRequest request) {
		try {
			AuthResponse response = authService.login(request.getEmail(), request.getPassword());
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			System.err.println("Erro ao fazer login: " + e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(500).body("Erro interno do servidor");
		}
	}
	
	@GetMapping("/me")
	public HttpEntity<Object> getCurrentUser() {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String email = authentication.getName();
			
			User user = userService.findByEmail(email);
			UserOutput output = new UserOutput(user.getId(), user.getEmail());
			
			return ResponseEntity.ok(output);
		} catch (Exception e) {
			System.err.println("Erro ao buscar usuário atual: " + e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(500).body("Erro interno do servidor");
		}
	}
}
