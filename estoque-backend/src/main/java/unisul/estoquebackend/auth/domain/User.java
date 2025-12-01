package unisul.estoquebackend.auth.domain;

public class User {
	
	private Long id;
	private String email;
	private String password;
	
	public User() {
	}
	
	public User(Long id, String email, String password) {
		this.setId(id);
		this.setEmail(email);
		this.setPassword(password);
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		if (email == null || email.trim().isEmpty()) {
			throw new IllegalArgumentException("O email não pode ser nulo ou vazio");
		}
		if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
			throw new IllegalArgumentException("Email inválido");
		}
		this.email = email.trim().toLowerCase();
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		if (password == null || password.trim().isEmpty()) {
			throw new IllegalArgumentException("A senha não pode ser nula ou vazia");
		}
		if (password.length() < 6) {
			throw new IllegalArgumentException("A senha deve ter no mínimo 6 caracteres");
		}
		this.password = password;
	}
}
