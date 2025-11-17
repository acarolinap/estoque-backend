package unisul.estoque_backend.category.controller.representation;

public class CategoryOutput {

	private Long id;
	private String name;
	private CategoryEnumRepresentation size;
	private CategoryEnumRepresentation packaging;
	
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
	public CategoryEnumRepresentation getSize() {
		return size;
	}
	public void setSize(CategoryEnumRepresentation size) {
		this.size = size;
	}
	public CategoryEnumRepresentation getPackaging() {
		return packaging;
	}
	public void setPackaging(CategoryEnumRepresentation packaging) {
		this.packaging = packaging;
	}
}
