package com.herusantoso.tokonezia.mapper;


import com.herusantoso.tokonezia.domain.User;
import com.herusantoso.tokonezia.dto.UserCreateDTO;
import com.herusantoso.tokonezia.dto.UserDetailDTO;
import com.herusantoso.tokonezia.dto.UserUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toEntity(UserCreateDTO dto, @MappingTarget User entity);

    User toEntity(UserUpdateDTO dto, @MappingTarget User entity);

    @Mapping(source = "secureId", target = "id")
    UserDetailDTO toDTO(User entity, @MappingTarget UserDetailDTO dto);

}

