package unisul.estoque_backend.category.mapper;

import unisul.estoque_backend.category.controller.representation.CategoryEnumRepresentation;
import unisul.estoque_backend.category.domain.Category;

public class CategoryEnumMapper {

	public static CategoryEnumRepresentation toRepresentation(Category.Size size) {
		
		String code = size.name();
		String friendlyName = size.getFriendlyName();
		
		CategoryEnumRepresentation representation = new CategoryEnumRepresentation(code, friendlyName);
		
		return representation;
	}
	
	public static CategoryEnumRepresentation toRepresentation(Category.Packaging packaging) {
		
		String code = packaging.name();
		String friendlyName = packaging.getFriendlyName();
		
		CategoryEnumRepresentation representation = new CategoryEnumRepresentation(code, friendlyName);
		
		return representation;
	}
}
