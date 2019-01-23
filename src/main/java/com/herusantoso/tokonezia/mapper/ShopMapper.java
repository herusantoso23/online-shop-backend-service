package com.herusantoso.tokonezia.mapper;


import com.herusantoso.tokonezia.domain.Shop;
import com.herusantoso.tokonezia.domain.User;
import com.herusantoso.tokonezia.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ShopMapper {

    ShopMapper INSTANCE = Mappers.getMapper(ShopMapper.class);

    Shop toEntity(ShopDTO dto, @MappingTarget Shop entity);

    @Mapping(source = "secureId", target = "id")
    ShopDetailDTO toDTO(Shop entity, @MappingTarget ShopDetailDTO dto);


}

