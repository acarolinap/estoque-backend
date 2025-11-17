package unisul.estoquebackend.category.controller.representation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import unisul.estoquebackend.category.domain.Category;

public class CategoryInput {

	private Long id;
	
	@NotBlank(message = "O nome da categoria é obrigatório")
	private String name;
	
	@NotNull(message = "O tamanho é obrigatório")
	private Category.Size size;
	
	@NotNull(message = "A embalagem é obrigatória")
	private Category.Packaging packaging;
	
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
	public Category.Size getSize() {
		return size;
	}
	public void setSize(Category.Size size) {
		this.size = size;
	}
	public Category.Packaging getPackaging() {
		return packaging;
	}
	public void setPackaging(Category.Packaging packaging) {
		this.packaging = packaging;
	}
}
