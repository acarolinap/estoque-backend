package unisul.estoquebackend.auth.exception;

public class UserNotFoundException extends RuntimeException {
	
	public UserNotFoundException(String email) {
		super("Usuário não encontrado: " + email);
	}
	
	public UserNotFoundException(Long id) {
		super("Usuário não encontrado com ID: " + id);
	}
}
