package com.herusantoso.tokonezia.service;

import com.herusantoso.tokonezia.domain.Payment;
import com.herusantoso.tokonezia.dto.PaymentDTO;

import java.math.BigDecimal;

public interface PaymentService {

    Payment create(String paymentMethode, String phoneNumber, BigDecimal totalPrice);

    String pay(PaymentDTO paymentDTO);
}
