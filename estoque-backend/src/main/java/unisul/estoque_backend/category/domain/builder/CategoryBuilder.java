package unisul.estoque_backend.category.domain.builder;

import unisul.estoque_backend.category.domain.Category;
import unisul.estoque_backend.category.domain.Category.*;

public class CategoryBuilder {

	private Long id;
	private String name;
	private Size size;
	private Packaging packaging;
	
	public CategoryBuilder id(Long id) {
		this.id = id;
		return this;
	}
	
	public CategoryBuilder name(String name) {
		this.name = name;
		return this;
	}
	
	public CategoryBuilder size(Size size) {
		this.size = size;
		return this;
	}
	
	public CategoryBuilder packaging(Packaging packaging) {
		this.packaging = packaging;
		return this;
	}
	
	public Category build() {
		Category category = new Category();
		category.setId(id);
		category.setName(name);
		category.setSize(size);
		category.setPackaging(packaging);
		return category;
	}
}
