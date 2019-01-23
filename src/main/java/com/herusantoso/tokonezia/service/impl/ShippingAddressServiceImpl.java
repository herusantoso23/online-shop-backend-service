package com.herusantoso.tokonezia.service.impl;


import com.herusantoso.tokonezia.domain.ShippingAddress;
import com.herusantoso.tokonezia.dto.ShippingAddressDTO;
import com.herusantoso.tokonezia.exception.BadRequestException;
import com.herusantoso.tokonezia.mapper.ShippingAddressMapper;
import com.herusantoso.tokonezia.repository.ShippingAddressRepository;
import com.herusantoso.tokonezia.security.AuthenticationFacade;
import com.herusantoso.tokonezia.service.ShippingAddressService;
import com.herusantoso.tokonezia.util.PaginationUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class ShippingAddressServiceImpl implements ShippingAddressService {

    @Autowired
    private ShippingAddressRepository shippingAddressRepository;

    @Autowired
    private AuthenticationFacade authenticationFacade;

    @Override
    public ShippingAddress create(ShippingAddressDTO shopDTO) {
        if(shopDTO.getZipCode().toString().length() != 5) throw new BadRequestException("zip code must be 5 digit");

        ShippingAddress shippingAddress = ShippingAddressMapper.INSTANCE.toEntity(shopDTO, new ShippingAddress());
        shippingAddress.setBuyer(authenticationFacade.getCurrentUser());
        ShippingAddress saved = shippingAddressRepository.saveAndFlush(shippingAddress);
        return saved;
    }

    @Override
    public ShippingAddress update(String id, ShippingAddressDTO shopDTO) {
        ShippingAddress shippingAddress = shippingAddressRepository.findOneBySecureId(id)
                .orElseThrow(()->new BadRequestException("Shipping address not found"));
        shippingAddress = ShippingAddressMapper.INSTANCE.toEntity(shopDTO, shippingAddress);
        shippingAddress.setBuyer(authenticationFacade.getCurrentUser());
        ShippingAddress saved = shippingAddressRepository.save(shippingAddress);
        return saved;
    }


    @Override
    public Map<String, Object> getAll(Integer page, Integer limit, String sortBy, String direction) {
        sortBy = StringUtils.isEmpty(sortBy) ? "creationDate" : sortBy;
        direction = StringUtils.isEmpty(direction) ? "desc" : direction;

        Pageable pageable = PageRequest.of(page,limit, PaginationUtil.getSortBy(direction),sortBy);
        Page<ShippingAddress> resultPage = shippingAddressRepository.findByBuyerAndDeletedFalse(authenticationFacade.getCurrentUser(), pageable);
        Collection<ShippingAddressDTO> vos = resultPage.getContent().stream()
                .map(entity -> ShippingAddressMapper.INSTANCE.toDTO(entity, new ShippingAddressDTO()))
                .collect(Collectors.toCollection(LinkedList::new));
        return PaginationUtil.constructMapReturn(vos, resultPage.getTotalElements(), resultPage.getTotalPages());
    }

    @Override
    public ShippingAddressDTO getDetail(String id) {
        ShippingAddress shippingAddress = shippingAddressRepository.findOneBySecureId(id)
                .orElseThrow(()-> new BadRequestException("Shipping address not found"));
        return ShippingAddressMapper.INSTANCE.toDTO(shippingAddress, new ShippingAddressDTO());
    }

    @Override
    public Boolean delete(String id) {
        ShippingAddress shippingAddress = shippingAddressRepository.findOneBySecureId(id)
                .orElseThrow(()-> new BadRequestException("Shipping address not found"));
        shippingAddress.setDeleted(Boolean.TRUE);
        shippingAddressRepository.save(shippingAddress);
        return Boolean.TRUE;
    }
}
