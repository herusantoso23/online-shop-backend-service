package com.herusantoso.tokonezia.service;

import com.herusantoso.tokonezia.dto.CartDTO;

import java.util.Map;

public interface CartService {

    Boolean addToCart(CartDTO cartDTO);

    Map<String, Object> getAll(Integer page, Integer limit, String sortBy, String direction);

    Boolean delete(String id);

}
