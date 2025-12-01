package unisul.estoquebackend.auth.exception;

public class InvalidCredentialsException extends RuntimeException {
	
	public InvalidCredentialsException() {
		super("Email ou senha inválidos");
	}
	
	public InvalidCredentialsException(String message) {
		super(message);
	}
}
