package com.herusantoso.tokonezia.mapper;


import com.herusantoso.tokonezia.domain.Product;
import com.herusantoso.tokonezia.domain.Shop;
import com.herusantoso.tokonezia.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    Product toEntity(ProductDTO dto, @MappingTarget Product entity);

    @Mapping(source = "secureId", target = "id")
    ProductListDTO toDTO(Product entity, @MappingTarget ProductListDTO dto);

    @Mapping(source = "secureId", target = "id")
    ProductDetailDTO toDTO(Product entity, @MappingTarget ProductDetailDTO dto);

}

