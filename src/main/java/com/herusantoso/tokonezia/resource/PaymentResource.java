package com.herusantoso.tokonezia.resource;

import com.herusantoso.tokonezia.dto.PaymentDTO;
import com.herusantoso.tokonezia.dto.ResultDTO;
import com.herusantoso.tokonezia.service.PaymentService;;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("api/v1")
public class PaymentResource {

    @Autowired
    private PaymentService paymentService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/payment/pay")
    public ResponseEntity<ResultDTO> createPurchase(@Valid @RequestBody PaymentDTO paymentDTO) {
        RequestHandler handler = new RequestHandler() {
            @Override
            public Object processRequest() {
                return paymentService.pay(paymentDTO);
            }
        };
        return handler.getResult();
    }

}
