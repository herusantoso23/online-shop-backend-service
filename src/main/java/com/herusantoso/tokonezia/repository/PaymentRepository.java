package com.herusantoso.tokonezia.repository;

import com.herusantoso.tokonezia.domain.Payment;
import com.herusantoso.tokonezia.domain.Purchase;
import com.herusantoso.tokonezia.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Optional<Payment> findOneBySecureId(String secureId);

    Optional<Payment> findOneByVirtualAccountAndAmountAndPaymentStatus(String virtualAccount, BigDecimal amount, String paymentStatus);

    Page<Payment> findByAndDeletedFalse(Pageable pageable);

}
