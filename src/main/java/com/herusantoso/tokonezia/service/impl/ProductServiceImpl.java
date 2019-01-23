package com.herusantoso.tokonezia.service.impl;

import com.herusantoso.tokonezia.domain.Product;
import com.herusantoso.tokonezia.dto.ProductDTO;
import com.herusantoso.tokonezia.dto.ProductDetailDTO;
import com.herusantoso.tokonezia.dto.ProductListDTO;
import com.herusantoso.tokonezia.dto.ShopDetailDTO;
import com.herusantoso.tokonezia.exception.BadRequestException;
import com.herusantoso.tokonezia.mapper.ProductMapper;
import com.herusantoso.tokonezia.mapper.ShopMapper;
import com.herusantoso.tokonezia.repository.ProductRepository;
import com.herusantoso.tokonezia.repository.ShopRepository;
import com.herusantoso.tokonezia.service.ProductService;
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
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ShopRepository shopRepository;

    @Override
    public Boolean create(ProductDTO productDTO) {
        Product product = ProductMapper.INSTANCE.toEntity(productDTO, new Product());
        product.setShop(shopRepository.findOneBySecureId(productDTO.getShopId())
                .orElseThrow(()-> new BadRequestException("Shop not found")));
        productRepository.save(product);
        return Boolean.TRUE;
    }

    @Override
    public Boolean update(String id, ProductDTO productDTO) {
        Product product = productRepository.findOneBySecureId(id)
                .orElseThrow(()-> new BadRequestException("Product not found"));
        product = ProductMapper.INSTANCE.toEntity(productDTO, product);
        product.setShop(shopRepository.findOneBySecureId(productDTO.getShopId())
                .orElseThrow(()-> new BadRequestException("Shop not found")));
        productRepository.save(product);
        return null;
    }

    @Override
    public Map<String, Object> getAll(Integer page, Integer limit, String sortBy, String direction) {
        sortBy = StringUtils.isEmpty(sortBy) ? "creationDate" : sortBy;
        direction = StringUtils.isEmpty(direction) ? "desc" : direction;

        Pageable pageable = PageRequest.of(page,limit, PaginationUtil.getSortBy(direction),sortBy);
        Page<Product> resultPage = productRepository.findByDeletedFalse(pageable);
        Collection<ProductListDTO> vos = resultPage.getContent().stream()
                .map(entity -> ProductMapper.INSTANCE.toDTO(entity, new ProductListDTO()))
                .collect(Collectors.toCollection(LinkedList::new));
        return PaginationUtil.constructMapReturn(vos, resultPage.getTotalElements(), resultPage.getTotalPages());
    }

    @Override
    public ProductDetailDTO getDetail(String id) {
        Product product = productRepository.findOneBySecureId(id)
                .orElseThrow(()-> new BadRequestException("Product not found"));
        ProductDetailDTO dto = ProductMapper.INSTANCE.toDTO(product, new ProductDetailDTO());
        dto.setShop(ShopMapper.INSTANCE.toDTO(product.getShop(), new ShopDetailDTO()));
        return dto;
    }

    @Override
    public Boolean delete(String id) {
        Product product = productRepository.findOneBySecureId(id)
                .orElseThrow(()-> new BadRequestException("Product not found"));
        product.setDeleted(Boolean.TRUE);
        productRepository.save(product);
        return Boolean.TRUE;
    }
}
