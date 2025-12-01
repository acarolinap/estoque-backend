package unisul.estoquebackend.report.controller.representation;

public class TopMovementOutput {
	
	private Long productId;
	private String productName;
	private Integer totalMovements;
	private String type;
	
	public TopMovementOutput() {
	}
	
	public TopMovementOutput(Long productId, String productName, Integer totalMovements, String type) {
		this.productId = productId;
		this.productName = productName;
		this.totalMovements = totalMovements;
		this.type = type;
	}
	
	public Long getProductId() {
		return productId;
	}
	
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	
	public String getProductName() {
		return productName;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public Integer getTotalMovements() {
		return totalMovements;
	}
	
	public void setTotalMovements(Integer totalMovements) {
		this.totalMovements = totalMovements;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
}
