package spring_boot.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import spring_boot.dto.UserDTO;
import spring_boot.dto.UserUpdate;
import spring_boot.entity.UserEntity;
import spring_boot.response.UserResponse;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserEntity toUser(UserDTO request);

    UserResponse toResponse(UserEntity user);
    void updateUser(@MappingTarget UserEntity user, UserUpdate request);
}
