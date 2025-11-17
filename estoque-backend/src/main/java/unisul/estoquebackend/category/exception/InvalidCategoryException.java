package unisul.estoquebackend.category.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exceção customizada para erros de validação específicos de categoria.
 * Retorna automaticamente HTTP 400 Bad Request quando lançada.
 * 
 * Use esta exceção quando uma regra de negócio específica de categoria for violada.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidCategoryException extends RuntimeException {

	/**
	 * Construtor que recebe a mensagem de erro.
	 * 
	 * @param message Mensagem descritiva do erro de validação
	 */
	public InvalidCategoryException(String message) {
		super(message);
	}
}

