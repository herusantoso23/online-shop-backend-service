package com.herusantoso.tokonezia.mapper;


import com.herusantoso.tokonezia.domain.Product;
import com.herusantoso.tokonezia.domain.Purchase;
import com.herusantoso.tokonezia.domain.PurchaseDetail;
import com.herusantoso.tokonezia.dto.ProductDTO;
import com.herusantoso.tokonezia.dto.ProductDetailDTO;
import com.herusantoso.tokonezia.dto.ProductListDTO;
import com.herusantoso.tokonezia.dto.PurchaseDetailDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PurchaseMapper {

    PurchaseMapper INSTANCE = Mappers.getMapper(PurchaseMapper.class);

    PurchaseDetail toEntity(PurchaseDetailDTO dto, @MappingTarget PurchaseDetail entity);


}

