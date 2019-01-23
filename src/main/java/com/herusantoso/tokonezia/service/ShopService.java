package com.herusantoso.tokonezia.service;

import com.herusantoso.tokonezia.dto.*;

import java.util.Map;

public interface ShopService {

    Boolean create(ShopDTO shopDTO);

    Boolean update(String id, ShopDTO shopDTO);

    Map<String, Object> getAll(Integer page, Integer limit, String sortBy, String direction);

    ShopDetailDTO getDetail(String id);

    Boolean delete(String id);
}
