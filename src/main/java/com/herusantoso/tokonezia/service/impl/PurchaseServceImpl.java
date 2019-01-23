package com.herusantoso.tokonezia.service.impl;


import com.herusantoso.tokonezia.domain.*;
import com.herusantoso.tokonezia.dto.PurchaseDTO;
import com.herusantoso.tokonezia.dto.PurchaseDetailDTO;
import com.herusantoso.tokonezia.dto.VirtualAccountDTO;
import com.herusantoso.tokonezia.exception.BadRequestException;
import com.herusantoso.tokonezia.mapper.PurchaseMapper;
import com.herusantoso.tokonezia.repository.*;
import com.herusantoso.tokonezia.security.AuthenticationFacade;
import com.herusantoso.tokonezia.service.PaymentService;
import com.herusantoso.tokonezia.service.PurchaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Transactional
@Slf4j
public class PurchaseServceImpl implements PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private PurchaseDetailRepository purchaseDetailRepository;

    @Autowired
    private AuthenticationFacade authenticationFacade;

    @Autowired
    private ShippingAddressServiceImpl shippingAddressService;

    @Autowired
    private ShippingAddressRepository shippingAddressRepository;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepository cartRepository;

    @Override
    public VirtualAccountDTO create(PurchaseDTO purchaseDTO) {

        Purchase purchase = new Purchase();
        purchase.setBuyer(authenticationFacade.getCurrentUser());

        Payment payment = paymentService.create(
                purchaseDTO.getPaymentMethode(),
                authenticationFacade.getCurrentUser().getPhoneNumber(),
                purchaseDTO.getTotalPrice());

        purchase.setPayment(payment);
        if(purchaseDTO.getShippingAddress().getId() == null){
            purchase.setShippingAddress(shippingAddressService.create(purchaseDTO.getShippingAddress()));
        } else {
            purchase.setShippingAddress(shippingAddressRepository.findOneBySecureId(purchaseDTO.getShippingAddress().getId())
                    .orElseThrow(()-> new BadRequestException("Shipping address not found")));
        }
        purchase.setTotalPrice(purchaseDTO.getTotalPrice());
        Purchase purchaseSaved = purchaseRepository.saveAndFlush(purchase);

        BigDecimal totalPrice = new BigDecimal(0);
        for(PurchaseDetailDTO purchaseDetailDTO : purchaseDTO.getPurchaseDetails()){
            Cart cart = cartRepository.findOneBySecureId(purchaseDetailDTO.getCartId())
                    .orElseThrow(() -> new BadRequestException("Cart not found"));

            totalPrice = totalPrice.add(cart.getProduct().getPrice().multiply(new BigDecimal(purchaseDetailDTO.getQuantity())));

            PurchaseDetail purchaseDetail = PurchaseMapper.INSTANCE.toEntity(purchaseDetailDTO, new PurchaseDetail());
            purchaseDetail.setPrice(cart.getProduct().getPrice().multiply(new BigDecimal(purchaseDetailDTO.getQuantity())));
            purchaseDetail.setPurchase(purchaseSaved);
            purchaseDetail.setProduct(cart.getProduct());
            purchaseDetailRepository.saveAndFlush(purchaseDetail);

            //cart delete
            cart.setPurchase(Boolean.TRUE);
            cartRepository.save(cart);
        }

        if(totalPrice.compareTo(purchaseDTO.getTotalPrice()) != 0)
            throw new BadRequestException("Total price is not valid");

        VirtualAccountDTO virtualAccountDTO = new VirtualAccountDTO();
        virtualAccountDTO.setAmount(payment.getAmount());
        virtualAccountDTO.setPaymentExpiredDate(payment.getPaymentExpiredDate().toEpochMilli());
        virtualAccountDTO.setVirtualAccount(payment.getVirtualAccount());

        return virtualAccountDTO;
    }
}
