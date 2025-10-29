package unisul.estoque_backend.category.controller.representation;

import unisul.estoque_backend.category.domain.Category;

public class CategoryInput {

	private String name;
	private Category.Size size;
	private Category.Packaging packaging;
	
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
