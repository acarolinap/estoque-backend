package unisul.estoquebackend.product.domain;

import unisul.estoquebackend.category.domain.Category;
import unisul.estoquebackend.product.exception.InvalidStockException;

public class Product {

	private Long id;
	private String name;
	private Integer quantity;
	private Integer minimumQuantity;
	private Integer maximumQuantity;
	private Integer price;
	private String unit;
	private Long categoryId;
	
	public Product() {
		// Default values
		this.quantity = 0;
		this.minimumQuantity = 0;
	}
	
	public Product(Long id, String name, Integer quantity, Integer minimumQuantity, 
			Integer maximumQuantity, Integer price, String unit, Long categoryId) {
		this.setId(id);
		this.setName(name);
		this.setQuantity(quantity);
		this.setMinimumQuantity(minimumQuantity);
		this.setMaximumQuantity(maximumQuantity);
		this.setPrice(price);
		this.setUnit(unit);
		this.setCategoryId(categoryId);
		validateStockLimits();
	}

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
		if (name == null || name.trim().isEmpty()) {
			throw new IllegalArgumentException("O nome do produto não pode ser nulo ou vazio");
		}
		this.name = name.trim();
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		if (quantity == null) {
			throw new IllegalArgumentException("A quantidade não pode ser nula");
		}
		if (quantity < 0) {
			throw new IllegalArgumentException("A quantidade não pode ser negativa");
		}
		this.quantity = quantity;
		validateStockLimits();
	}

	public Integer getMinimumQuantity() {
		return minimumQuantity;
	}

	public void setMinimumQuantity(Integer minimumQuantity) {
		if (minimumQuantity == null) {
			throw new IllegalArgumentException("A quantidade mínima não pode ser nula");
		}
		if (minimumQuantity < 0) {
			throw new IllegalArgumentException("A quantidade mínima não pode ser negativa");
		}
		this.minimumQuantity = minimumQuantity;
		validateStockLimits();
	}

	public Integer getMaximumQuantity() {
		return maximumQuantity;
	}

	public void setMaximumQuantity(Integer maximumQuantity) {
		// maximumQuantity pode ser null, mas se não for null, deve ser >= minimumQuantity
		if (maximumQuantity != null && maximumQuantity < 0) {
			throw new IllegalArgumentException("A quantidade máxima não pode ser negativa");
		}
		this.maximumQuantity = maximumQuantity;
		validateStockLimits();
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		if (price == null) {
			throw new IllegalArgumentException("O preço não pode ser nulo");
		}
		if (price < 0) {
			throw new IllegalArgumentException("O preço não pode ser negativo");
		}
		this.price = price;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		if (unit == null || unit.trim().isEmpty()) {
			throw new IllegalArgumentException("A unidade não pode ser nula ou vazia");
		}
		this.unit = unit.trim();
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		if (categoryId == null || categoryId <= 0) {
			throw new IllegalArgumentException("O ID da categoria deve ser um número positivo");
		}
		this.categoryId = categoryId;
	}
	
	private void validateStockLimits() {
		// Só valida se todos os valores necessários estiverem definidos
		if (minimumQuantity != null && maximumQuantity != null) {
			if (maximumQuantity < minimumQuantity) {
				throw new InvalidStockException("A quantidade máxima não pode ser menor que a quantidade mínima");
			}
		}
		// Validações de quantidade vs limites só fazem sentido se os limites estiverem definidos
		// e a quantidade também estiver definida
		if (minimumQuantity != null && quantity != null && quantity < minimumQuantity) {
			throw new InvalidStockException("A quantidade atual não pode ser menor que a quantidade mínima");
		}
		if (maximumQuantity != null && quantity != null && quantity > maximumQuantity) {
			throw new InvalidStockException("A quantidade atual não pode ser maior que a quantidade máxima");
		}
	}
}
