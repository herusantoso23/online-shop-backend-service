package com.herusantoso.tokonezia.service;

import com.herusantoso.tokonezia.domain.ShippingAddress;
import com.herusantoso.tokonezia.dto.ShippingAddressDTO;

import java.util.Map;

public interface ShippingAddressService {

    ShippingAddress create(ShippingAddressDTO shopDTO);

    ShippingAddress update(String id, ShippingAddressDTO shopDTO);

    Map<String, Object> getAll(Integer page, Integer limit, String sortBy, String direction);

    ShippingAddressDTO getDetail(String id);

    Boolean delete(String id);
}
