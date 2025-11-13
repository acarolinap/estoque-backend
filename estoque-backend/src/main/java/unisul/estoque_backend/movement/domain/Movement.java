package unisul.estoque_backend.movement.domain;

import java.time.Instant;

public class Movement {

	public enum Type {
		IN("Entrada"),
		OUT("Saída");
		
		private final String friendlyName;
		
		private Type(String friendlyName) {
			this.friendlyName = friendlyName;
		}
		
		public String getFriendlyName() {
			return friendlyName;
		}
	}
	
	private Long id;
	private Instant dateTime;
	private Integer quantity;
	private Type type;
	private Long productId;
	
	public Movement() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getDateTime() {
		return dateTime;
	}

	public void setDateTime(Instant dateTime) {
		this.dateTime = dateTime;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}
}
