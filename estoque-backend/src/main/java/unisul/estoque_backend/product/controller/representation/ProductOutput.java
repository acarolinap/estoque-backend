package unisul.estoque_backend.product.controller.representation;

public class ProductOutput {

	private Long id;
	private String name;
	private Integer quantity;
	private Integer minimumQuantity;
	private Integer maximumQuantity;
	private boolean lowQuantity;
	private Integer price;
	private String unit;
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
	public boolean isLowQuantity() {
		return lowQuantity;
	}
	public void setLowQuantity(boolean lowQuantity) {
		this.lowQuantity = lowQuantity;
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
