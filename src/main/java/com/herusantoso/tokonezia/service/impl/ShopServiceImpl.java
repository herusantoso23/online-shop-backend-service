package com.herusantoso.tokonezia.service.impl;

import com.herusantoso.tokonezia.domain.Shop;
import com.herusantoso.tokonezia.dto.ShopDTO;
import com.herusantoso.tokonezia.dto.ShopDetailDTO;
import com.herusantoso.tokonezia.exception.BadRequestException;
import com.herusantoso.tokonezia.mapper.ShopMapper;
import com.herusantoso.tokonezia.repository.ShopRepository;
import com.herusantoso.tokonezia.security.AuthenticationFacade;
import com.herusantoso.tokonezia.service.ShopService;
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
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private AuthenticationFacade authenticationFacade;

    @Override
    public Boolean create(ShopDTO shopDTO) {
        Shop shop = ShopMapper.INSTANCE.toEntity(shopDTO, new Shop());
        shop.setSeller(authenticationFacade.getCurrentUser());
        shopRepository.save(shop);
        return Boolean.TRUE;
    }

    @Override
    public Boolean update(String id, ShopDTO shopDTO) {
        Shop shop = shopRepository.findOneBySecureId(id)
                .orElseThrow(()-> new BadRequestException("Shop not found"));
        shop = ShopMapper.INSTANCE.toEntity(shopDTO, shop);
        shopRepository.save(shop);
        return Boolean.TRUE;
    }

    @Override
    public Map<String, Object> getAll(Integer page, Integer limit, String sortBy, String direction) {
        sortBy = StringUtils.isEmpty(sortBy) ? "creationDate" : sortBy;
        direction = StringUtils.isEmpty(direction) ? "desc" : direction;

        Pageable pageable = PageRequest.of(page,limit, PaginationUtil.getSortBy(direction),sortBy);
        Page<Shop> resultPage = shopRepository.findBySellerAndDeletedFalse(authenticationFacade.getCurrentUser(), pageable);
        Collection<ShopDetailDTO> vos = resultPage.getContent().stream()
                .map(entity -> ShopMapper.INSTANCE.toDTO(entity, new ShopDetailDTO()))
                .collect(Collectors.toCollection(LinkedList::new));
        return PaginationUtil.constructMapReturn(vos, resultPage.getTotalElements(), resultPage.getTotalPages());
    }

    @Override
    public ShopDetailDTO getDetail(String id) {
        Shop shop = shopRepository.findOneBySecureId(id)
                .orElseThrow(()-> new BadRequestException("Shop not found"));
        return ShopMapper.INSTANCE.toDTO(shop, new ShopDetailDTO());
    }

    @Override
    public Boolean delete(String id) {
        Shop shop = shopRepository.findOneBySecureId(id)
                .orElseThrow(()-> new BadRequestException("Shop not found"));
        shop.setDeleted(Boolean.TRUE);
        shopRepository.save(shop);
        return Boolean.TRUE;
    }
}
