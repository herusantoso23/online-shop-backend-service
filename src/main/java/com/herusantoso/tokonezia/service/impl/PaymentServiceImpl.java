package com.herusantoso.tokonezia.service.impl;

import com.herusantoso.tokonezia.domain.Payment;
import com.herusantoso.tokonezia.domain.Product;
import com.herusantoso.tokonezia.domain.Purchase;
import com.herusantoso.tokonezia.domain.User;
import com.herusantoso.tokonezia.dto.PaymentDTO;
import com.herusantoso.tokonezia.exception.BadRequestException;
import com.herusantoso.tokonezia.repository.*;
import com.herusantoso.tokonezia.service.PaymentService;
import com.herusantoso.tokonezia.util.Constants;
import com.herusantoso.tokonezia.util.GeneratorUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
@Transactional
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PurchaseDetailRepository purchaseDetailRepository;

    @Override
    public Payment create(String paymentMethode, String phoneNumber, BigDecimal totalPrice){
        if(!StringUtils.isNumeric(phoneNumber))
            throw new BadRequestException("Phone number is invalid");

        Payment payment = new Payment();
        payment.setPaymentExpiredDate(Instant.now().plus(12, ChronoUnit.HOURS));
        if(paymentMethode.equalsIgnoreCase(Constants.PaymentMethode.VIRTUAL_ACCOUNT_BCA)){
            payment.setPaymentMethode(Constants.PaymentMethode.VIRTUAL_ACCOUNT_BCA);
            payment.setVirtualAccount("70001"+phoneNumber);
        } else {
            throw new BadRequestException("Payment methode is invalid");
        }
        payment.setAmount(totalPrice.add(new BigDecimal(GeneratorUtil.generateAmountCode())));
        Payment saved = paymentRepository.saveAndFlush(payment);
        return saved;
    }

    @Override
    public String pay(PaymentDTO paymentDTO){
        if(!StringUtils.isNumeric(paymentDTO.getVirtualAccount()))
            throw new BadRequestException("Virtual account is invalid");

        Payment payment = paymentRepository.findOneByVirtualAccountAndAmountAndPaymentStatus(paymentDTO.getVirtualAccount(), paymentDTO.getAmount(), Constants.PaymentStatus.WAITING_FOR_PAYMENT)
                .orElseThrow(() -> new BadRequestException("Virtual Account Not Found"));
        Instant now = Instant.now();

        int result = now.compareTo(payment.getPaymentExpiredDate());
        if(result < 1){
            payment.setPaymentDate(now);
            payment.setPaymentStatus(Constants.PaymentStatus.ALREADY_PAID);
            log.info("succes");
        } else {
            payment.setPaymentStatus(Constants.PaymentStatus.EXPIRED);
            log.info("expired");
        }
        Payment saved = paymentRepository.saveAndFlush(payment);

        if(saved.getPaymentStatus().equalsIgnoreCase(Constants.PaymentStatus.ALREADY_PAID)){
            Purchase purchase = purchaseRepository.findOneByPayment(payment)
                    .orElseThrow(()-> new BadRequestException("Purchase not found"));
            BigDecimal moneyReturn = paymentDTO.getAmount().subtract(purchase.getTotalPrice());

            //update balance for buyer
            User buyer = purchase.getBuyer();
            buyer.setBalance(buyer.getBalance().add(moneyReturn));
            userRepository.save(buyer);

            purchaseDetailRepository.findByPurchase(purchase)
                    .stream()
                    .forEach(purchaseDetail -> {
                        //update balance for seller
                        User seller = purchaseDetail.getProduct().getShop().getSeller();
                        seller.setBalance(seller.getBalance().add(purchaseDetail.getPrice()));
                        userRepository.save(seller);

                        //update quantity and sold of product
                        Product product = purchaseDetail.getProduct();
                        product.setQuantity(product.getQuantity() - purchaseDetail.getQuantity());
                        product.setSold(product.getSold() + purchaseDetail.getQuantity());
                        productRepository.save(product);
                    });

            return "Payment Success";
        } else {
            return "Virtual Account Expired";
        }
    }



}
