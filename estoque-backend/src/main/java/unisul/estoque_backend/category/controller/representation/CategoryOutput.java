package unisul.estoque_backend.category.controller.representation;

public class CategoryOutput {

	private Long id;
	private String name;
	private EnumRepresentation size;
	private EnumRepresentation packaging;
	
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
	public EnumRepresentation getSize() {
		return size;
	}
	public void setSize(EnumRepresentation size) {
		this.size = size;
	}
	public EnumRepresentation getPackaging() {
		return packaging;
	}
	public void setPackaging(EnumRepresentation packaging) {
		this.packaging = packaging;
	}
}
