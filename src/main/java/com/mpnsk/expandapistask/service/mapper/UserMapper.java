package com.mpnsk.expandapistask.service.mapper;

import com.mpnsk.expandapistask.config.MapperConfig;
import com.mpnsk.expandapistask.dto.request.UserRegistrationDto;
import com.mpnsk.expandapistask.dto.response.UserResponseDto;
import com.mpnsk.expandapistask.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    UserResponseDto mapToDto(User user);
    @Mapping(target = "id", ignore = true)
    User mapToEntity(UserRegistrationDto userDto);
}
