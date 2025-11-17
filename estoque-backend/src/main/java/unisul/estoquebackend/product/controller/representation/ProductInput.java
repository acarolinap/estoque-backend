package unisul.estoquebackend.product.controller.representation;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@ValidStockLimits
public class ProductInput {

	private Long id;
	
	@NotBlank(message = "O nome do produto é obrigatório")
	private String name;
	
	@NotNull(message = "A quantidade é obrigatória")
	@Min(value = 0, message = "A quantidade não pode ser negativa")
	private Integer quantity;
	
	@NotNull(message = "A quantidade mínima é obrigatória")
	@Min(value = 0, message = "A quantidade mínima não pode ser negativa")
	private Integer minimumQuantity;
	
	private Integer maximumQuantity;
	
	@NotNull(message = "O preço é obrigatório")
	@Min(value = 0, message = "O preço não pode ser negativo")
	private Integer price;
	
	@NotBlank(message = "A unidade é obrigatória")
	private String unit;
	
	@NotNull(message = "O ID da categoria é obrigatório")
	@Positive(message = "O ID da categoria deve ser um número positivo")
	private Long categoryId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getMinimumQuantity() {
		return minimumQuantity;
	}
	public void setMinimumQuantity(Integer minimumQuantity) {
		this.minimumQuantity = minimumQuantity;
	}
	public Integer getMaximumQuantity() {
		return maximumQuantity;
	}
	public void setMaximumQuantity(Integer maximumQuantity) {
		this.maximumQuantity = maximumQuantity;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
}
