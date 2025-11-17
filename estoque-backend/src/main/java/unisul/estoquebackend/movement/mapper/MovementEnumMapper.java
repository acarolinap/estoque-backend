package unisul.estoquebackend.movement.mapper;

import unisul.estoquebackend.movement.controller.representation.MovementEnumRepresentation;
import unisul.estoquebackend.movement.domain.Movement;

public class MovementEnumMapper {

	public static MovementEnumRepresentation toRepresentation(Movement.Type type) {
		String name = type.name();
		String friendlyName = type.getFriendlyName();
		
		MovementEnumRepresentation representation = new MovementEnumRepresentation(name, friendlyName);
		
		return representation;
	}
}
