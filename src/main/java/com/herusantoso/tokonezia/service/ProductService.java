package com.herusantoso.tokonezia.service;

import com.herusantoso.tokonezia.dto.ProductDTO;
import com.herusantoso.tokonezia.dto.ProductDetailDTO;

import java.util.Map;

public interface ProductService {

    Boolean create(ProductDTO productDTO);

    Boolean update(String id, ProductDTO productDTO);

    Map<String, Object> getAll(Integer page, Integer limit, String sortBy, String direction, String productName, String category);

    ProductDetailDTO getDetail(String id);

    Boolean delete(String id);
}
