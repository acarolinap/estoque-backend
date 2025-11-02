package unisul.estoque_backend.category.mapper;

import unisul.estoque_backend.category.controller.representation.EnumRepresentation;
import unisul.estoque_backend.category.domain.Category;

public class CategoryEnumMapper {

	public static EnumRepresentation toRepresentation(Category.Size size) {
		
		String code = size.name();
		String friendlyName = size.getFriendlyName();
		
		EnumRepresentation representation = new EnumRepresentation(code, friendlyName);
		
		return representation;
	}
	
	public static EnumRepresentation toRepresentation(Category.Packaging packaging) {
		
		String code = packaging.name();
		String friendlyName = packaging.getFriendlyName();
		
		EnumRepresentation representation = new EnumRepresentation(code, friendlyName);
		
		return representation;
	}
}
