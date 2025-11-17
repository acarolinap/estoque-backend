package unisul.estoquebackend.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

/**
 * Handler global para tratamento de exceções de validação.
 * Captura exceções lançadas em qualquer controller e retorna respostas HTTP apropriadas
 * com mensagens de erro claras em português.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * Trata exceções de validação do Bean Validation (@Valid, @NotNull, etc.).
	 * Retorna HTTP 400 Bad Request com detalhes dos campos inválidos.
	 * 
	 * @param ex Exceção contendo os erros de validação
	 * @return ResponseEntity com status 400 e mapa de erros por campo
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
		// Coleta todos os erros de validação por campo
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		
		Map<String, Object> response = new HashMap<>();
		response.put("message", "Erro de validação");
		response.put("errors", errors);
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}

	/**
	 * Trata violações de constraints de validação (validações em nível de método/parâmetro).
	 * Retorna HTTP 400 Bad Request com detalhes das violações.
	 * 
	 * @param ex Exceção contendo as violações de constraint
	 * @return ResponseEntity com status 400 e mapa de erros
	 */
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Map<String, Object>> handleConstraintViolationException(ConstraintViolationException ex) {
		Map<String, String> errors = new HashMap<>();
		for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
			String fieldName = violation.getPropertyPath().toString();
			String errorMessage = violation.getMessage();
			errors.put(fieldName, errorMessage);
		}
		
		Map<String, Object> response = new HashMap<>();
		response.put("message", "Erro de validação");
		response.put("errors", errors);
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}

	/**
	 * Trata erros de tipo em parâmetros de requisição (ex: string onde espera-se número).
	 * Retorna HTTP 400 Bad Request com mensagem descritiva.
	 * 
	 * @param ex Exceção contendo informações sobre o parâmetro inválido
	 * @return ResponseEntity com status 400 e mensagem de erro
	 */
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<Map<String, Object>> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
		Map<String, Object> response = new HashMap<>();
		response.put("message", "Parâmetro inválido: " + ex.getName());
		response.put("error", "O valor '" + ex.getValue() + "' não é válido para o parâmetro " + ex.getName());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}

	/**
	 * Trata argumentos ilegais lançados pelas camadas de domínio ou serviço.
	 * Retorna HTTP 400 Bad Request com a mensagem da exceção.
	 * 
	 * @param ex Exceção contendo a mensagem de erro
	 * @return ResponseEntity com status 400 e mensagem de erro
	 */
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Map<String, Object>> handleIllegalArgumentException(IllegalArgumentException ex) {
		Map<String, Object> response = new HashMap<>();
		response.put("message", "Erro de validação");
		response.put("error", ex.getMessage());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}
}

