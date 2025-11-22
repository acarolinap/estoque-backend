package unisul.estoquebackend.product.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class QuantityConflictException extends RuntimeException {

	public QuantityConflictException(String message) {
		super(message);
	}
}
