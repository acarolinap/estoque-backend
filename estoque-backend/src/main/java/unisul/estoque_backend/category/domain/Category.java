package unisul.estoque_backend.category.domain;

public class Category {

	public enum Size {
		SMALL, MEDIUM, LARGE
	}
	public enum Packaging {
		PAPER, CARDBOARD, PLASTIC, TIN, GLASS, WOOD, CLOTH
	}

	private Long id;
	private String name;
	private Size size;
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
