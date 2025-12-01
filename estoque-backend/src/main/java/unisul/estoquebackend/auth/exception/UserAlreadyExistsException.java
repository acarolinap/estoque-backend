package unisul.estoquebackend.auth.exception;

public class UserAlreadyExistsException extends RuntimeException {
	
	public UserAlreadyExistsException(String email) {
		super("Usuário já existe com o email: " + email);
	}
}
