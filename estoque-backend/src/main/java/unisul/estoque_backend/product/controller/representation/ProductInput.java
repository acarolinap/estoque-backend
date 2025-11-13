package unisul.estoque_backend.product.controller.representation;

	import jakarta.validation.constraints.NotEmpty;
	import jakarta.validation.constraints.NotNull;
	import jakarta.validation.constraints.Positive;
	import jakarta.validation.constraints.PositiveOrZero;
	import jakarta.validation.constraints.Size;

public class ProductInput {
	
	private Long id;
	private String name;
		@NotNull(message = "O nome não pode ser nulo")
    	@NotEmpty(message = "O nome não pode ser vazio")
    	@Size(min = 3, message = "O nome deve ter no mínimo 3 caracteres")
	private Integer quantity;
		@NotNull(message = "A quantidade não pode ser nula")
    	@PositiveOrZero(message = "A quantidade deve ser 0 ou maior")
	private Integer minimumQuantity;
		@NotNull(message = "A quantidade mínima não pode ser nula")
    	@PositiveOrZero(message = "A quantidade mínima deve ser 0 ou maior")
	private Integer maximumQuantity;
		@Positive(message = "A quantidade máxima deve ser um valor positivo")
	private Integer price;
		@NotNull(message = "O preço não pode ser nulo")
   		@Positive(message = "O preço deve ser um valor positivo")
	private String unit;
		@NotNull(message = "A unidade não pode ser nula")
    	@NotEmpty(message = "A unidade não pode ser vazia")
	private Long categoryId;
		@NotNull(message = "O ID da Categoria não pode ser nulo")
	
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
