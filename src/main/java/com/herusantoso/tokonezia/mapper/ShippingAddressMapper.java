package com.herusantoso.tokonezia.mapper;


import com.herusantoso.tokonezia.domain.Product;
import com.herusantoso.tokonezia.domain.ShippingAddress;
import com.herusantoso.tokonezia.dto.ProductDTO;
import com.herusantoso.tokonezia.dto.ProductDetailDTO;
import com.herusantoso.tokonezia.dto.ProductListDTO;
import com.herusantoso.tokonezia.dto.ShippingAddressDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ShippingAddressMapper {

    ShippingAddressMapper INSTANCE = Mappers.getMapper(ShippingAddressMapper.class);

    ShippingAddress toEntity(ShippingAddressDTO dto, @MappingTarget ShippingAddress entity);

    @Mapping(source = "secureId", target = "id")
    ShippingAddressDTO toDTO(ShippingAddress entity, @MappingTarget ShippingAddressDTO dto);


}

