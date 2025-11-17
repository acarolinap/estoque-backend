package unisul.estoquebackend.product.controller.representation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StockLimitsValidator implements ConstraintValidator<ValidStockLimits, ProductInput> {

	@Override
	public void initialize(ValidStockLimits constraintAnnotation) {
	}

	@Override
	public boolean isValid(ProductInput input, ConstraintValidatorContext context) {
		if (input == null) {
			return true;
		}
		
		Integer minimumQuantity = input.getMinimumQuantity();
		Integer maximumQuantity = input.getMaximumQuantity();
		Integer quantity = input.getQuantity();
		
		// Se maximumQuantity não for null, deve ser >= minimumQuantity
		if (maximumQuantity != null && minimumQuantity != null) {
			if (maximumQuantity < minimumQuantity) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(
						"A quantidade máxima não pode ser menor que a quantidade mínima")
						.addPropertyNode("maximumQuantity")
						.addConstraintViolation();
				return false;
			}
		}
		
		// Se quantity for menor que minimumQuantity
		if (quantity != null && minimumQuantity != null && quantity < minimumQuantity) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(
					"A quantidade atual não pode ser menor que a quantidade mínima")
					.addPropertyNode("quantity")
					.addConstraintViolation();
			return false;
		}
		
		// Se quantity for maior que maximumQuantity
		if (quantity != null && maximumQuantity != null && quantity > maximumQuantity) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(
					"A quantidade atual não pode ser maior que a quantidade máxima")
					.addPropertyNode("quantity")
					.addConstraintViolation();
			return false;
		}
		
		return true;
	}
}

