package unisul.estoquebackend.report.controller.representation;

import java.math.BigDecimal;

public class BalanceReportOutput {
	
	private Integer totalProducts;
	private Integer totalQuantity;
	private BigDecimal totalValue;
	
	public BalanceReportOutput() {
	}
	
	public BalanceReportOutput(Integer totalProducts, Integer totalQuantity, BigDecimal totalValue) {
		this.totalProducts = totalProducts;
		this.totalQuantity = totalQuantity;
		this.totalValue = totalValue;
	}
	
	public Integer getTotalProducts() { return totalProducts; }
	public void setTotalProducts(Integer totalProducts) { this.totalProducts = totalProducts; }
	
	public Integer getTotalQuantity() { return totalQuantity; }
	public void setTotalQuantity(Integer totalQuantity) { this.totalQuantity = totalQuantity; }
	
	public BigDecimal getTotalValue() { return totalValue; }
	public void setTotalValue(BigDecimal totalValue) { this.totalValue = totalValue; }
}
