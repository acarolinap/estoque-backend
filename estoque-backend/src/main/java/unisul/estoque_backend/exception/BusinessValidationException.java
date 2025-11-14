package unisul.estoque_backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exceção customizada para erros de validação de regra de negócio.
 * Retorna automaticamente HTTP 400 Bad Request quando lançada.
 * 
 * Use esta exceção quando uma regra de negócio específica for violada.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BusinessValidationException extends RuntimeException {

	/**
	 * Construtor que recebe a mensagem de erro.
	 * 
	 * @param message Mensagem descritiva do erro de validação
	 */
	public BusinessValidationException(String message) {
		super(message);
	}
}

