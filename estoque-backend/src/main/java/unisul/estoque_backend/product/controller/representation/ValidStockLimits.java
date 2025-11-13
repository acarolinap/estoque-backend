package unisul.estoque_backend.product.controller.representation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = StockLimitsValidator.class)
@Documented
public @interface ValidStockLimits {
	String message() default "A quantidade máxima deve ser maior ou igual à quantidade mínima";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}

