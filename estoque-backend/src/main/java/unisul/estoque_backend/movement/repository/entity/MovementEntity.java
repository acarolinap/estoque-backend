package unisul.estoque_backend.movement.repository.entity;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import unisul.estoque_backend.movement.domain.Movement;
import unisul.estoque_backend.product.repository.entity.ProductEntity;

@Entity
public class MovementEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@CreationTimestamp
	@Column(nullable = false, updatable = false)
	private Instant dateTime;
	@Column(nullable = false, updatable = false)
	private Integer quantity;
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, updatable = false)
	private Movement.Type type;
	@ManyToOne
	@JoinColumn(nullable = false, updatable = false)
	private ProductEntity productId;
	
	public MovementEntity() {
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

	public Movement.Type getType() {
		return type;
	}

	public void setType(Movement.Type type) {
		this.type = type;
	}

	public ProductEntity getProductId() {
		return productId;
	}

	public void setProductId(ProductEntity productId) {
		this.productId = productId;
	}
}
