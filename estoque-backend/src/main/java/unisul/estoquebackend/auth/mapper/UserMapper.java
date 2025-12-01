package unisul.estoquebackend.auth.mapper;

import unisul.estoquebackend.auth.domain.User;
import unisul.estoquebackend.auth.repository.entity.UserEntity;

public class UserMapper {
	
	public static User toDomain(UserEntity entity) {
		if (entity == null) {
			return null;
		}
		
		User user = new User();
		user.setId(entity.getId());
		user.setEmail(entity.getEmail());
		user.setPassword(entity.getPassword());
		
		return user;
	}
	
	public static UserEntity toEntity(User user) {
		if (user == null) {
			return null;
		}
		
		UserEntity entity = new UserEntity();
		entity.setId(user.getId());
		entity.setEmail(user.getEmail());
		entity.setPassword(user.getPassword());
		
		return entity;
	}
}
