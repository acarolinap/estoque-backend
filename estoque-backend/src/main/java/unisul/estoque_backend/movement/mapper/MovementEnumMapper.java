package unisul.estoque_backend.movement.mapper;

import unisul.estoque_backend.movement.controller.representation.MovementEnumRepresentation;
import unisul.estoque_backend.movement.domain.Movement;

public class MovementEnumMapper {

	public static MovementEnumRepresentation toRepresentation(Movement.Type type) {
		String name = type.name();
		String friendlyName = type.getFriendlyName();
		
		MovementEnumRepresentation representation = new MovementEnumRepresentation(name, friendlyName);
		
		return representation;
	}
}
