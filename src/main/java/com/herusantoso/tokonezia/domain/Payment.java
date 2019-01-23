package com.herusantoso.tokonezia.domain;


import com.herusantoso.tokonezia.util.Constants;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

@Data
@Entity
@Table(name="payment")
public class Payment extends BaseDomain implements Serializable {

    @Column(name = "payment_methode")
    private String paymentMethode;

    @Column(name = "payment_expired_date")
    private Instant paymentExpiredDate;

    @Column(name = "payment_date")
    private Instant paymentDate;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "virtual_account")
    private String virtualAccount;

    @Column(name = "payment_status")
    private String paymentStatus;

    @PrePersist
    public void prePersist(){
        super.prePersist();
        this.paymentStatus = Constants.PaymentStatus.WAITING_FOR_PAYMENT;

    }

}
