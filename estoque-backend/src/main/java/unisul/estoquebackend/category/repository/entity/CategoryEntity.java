package unisul.estoquebackend.category.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import unisul.estoquebackend.category.domain.Category;
import unisul.estoquebackend.category.domain.Category.Packaging;
import unisul.estoquebackend.category.domain.Category.Size;

@Entity
@Table(name = "category")
public class CategoryEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	@Enumerated(EnumType.STRING)
	private Category.Size size;
	@Enumerated(EnumType.STRING)
	private Category.Packaging packaging;
	
	public CategoryEntity() {
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
		this.name = name;
	}
	public Size getSize() {
		return size;
	}
	public void setSize(Size size) {
		this.size = size;
	}
	public Packaging getPackaging() {
		return packaging;
	}
	public void setPackaging(Packaging packaging) {
		this.packaging = packaging;
	}
}
