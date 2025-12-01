package unisul.estoquebackend.report.controller.representation;

import java.math.BigDecimal;

public class CategoryReportOutput {
	
	private Long categoryId;
	private String categoryName;
	private Integer totalProducts;
	private Integer totalQuantity;
	private BigDecimal totalValue;
	
	public CategoryReportOutput() {}
	
	public CategoryReportOutput(Long categoryId, String categoryName, Integer totalProducts, 
			Integer totalQuantity, BigDecimal totalValue) {
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.totalProducts = totalProducts;
		this.totalQuantity = totalQuantity;
		this.totalValue = totalValue;
	}
	
	public Long getCategoryId() { return categoryId; }
	public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }
	
	public String getCategoryName() { return categoryName; }
	public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
	
	public Integer getTotalProducts() { return totalProducts; }
	public void setTotalProducts(Integer totalProducts) { this.totalProducts = totalProducts; }
	
	public Integer getTotalQuantity() { return totalQuantity; }
	public void setTotalQuantity(Integer totalQuantity) { this.totalQuantity = totalQuantity; }
	
	public BigDecimal getTotalValue() { return totalValue; }
	public void setTotalValue(BigDecimal totalValue) { this.totalValue = totalValue; }
}
