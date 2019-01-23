package com.herusantoso.tokonezia.service.impl;


import com.herusantoso.tokonezia.domain.Cart;
import com.herusantoso.tokonezia.dto.CartDTO;
import com.herusantoso.tokonezia.dto.CartListDTO;
import com.herusantoso.tokonezia.dto.ProductListDTO;
import com.herusantoso.tokonezia.exception.BadRequestException;
import com.herusantoso.tokonezia.mapper.ProductMapper;
import com.herusantoso.tokonezia.repository.CartRepository;
import com.herusantoso.tokonezia.repository.ProductRepository;
import com.herusantoso.tokonezia.security.AuthenticationFacade;
import com.herusantoso.tokonezia.service.CartService;
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
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AuthenticationFacade authenticationFacade;

    @Override
    public Boolean addToCart(CartDTO cartDTO) {
        Cart cart = new Cart();
        cart.setBuyer(authenticationFacade.getCurrentUser());
        cart.setProduct(productRepository.findOneBySecureId(cartDTO.getProductId())
                .orElseThrow(()-> new BadRequestException("Product not found")));
        cartRepository.save(cart);
        return Boolean.TRUE;
    }

    @Override
    public Map<String, Object> getAll(Integer page, Integer limit, String sortBy, String direction) {
        sortBy = StringUtils.isEmpty(sortBy) ? "creationDate" : sortBy;
        direction = StringUtils.isEmpty(direction) ? "desc" : direction;

        Pageable pageable = PageRequest.of(page,limit, PaginationUtil.getSortBy(direction),sortBy);
        Page<Cart> resultPage = cartRepository.findByBuyerAndPurchaseFalseAndDeletedFalse(authenticationFacade.getCurrentUser(), pageable);
        Collection<CartListDTO> vos = resultPage.getContent().stream()
                .map(entity -> {
                    CartListDTO cartListDTO = new CartListDTO();
                    cartListDTO.setId(entity.getSecureId());
                    cartListDTO.setProduct(ProductMapper.INSTANCE.toDTO(entity.getProduct(), new ProductListDTO()));
                    return cartListDTO;
                })
                .collect(Collectors.toCollection(LinkedList::new));
        return PaginationUtil.constructMapReturn(vos, resultPage.getTotalElements(), resultPage.getTotalPages());
    }

    @Override
    public Boolean delete(String id) {
        Cart cart = cartRepository.findOneBySecureId(id)
                .orElseThrow(() -> new BadRequestException("Cart not found"));
        cart.setDeleted(Boolean.TRUE);
        cartRepository.save(cart);
        return Boolean.TRUE;
    }
}
