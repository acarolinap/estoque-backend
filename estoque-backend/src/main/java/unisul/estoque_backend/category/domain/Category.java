package unisul.estoque_backend.category.domain;

import jakarta.persistence.*;

@Entity
public class Category {

	public enum Size {
		SMALL, MEDIUM, LARGE
	}
	public enum Packaging {
		TIN, GLASS, PLASTIC
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	@Enumerated
	private Size size;
	@Enumerated
	private Packaging packaging;
	
	public Category() {
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
