package com.herusantoso.tokonezia.repository;

import com.herusantoso.tokonezia.domain.Payment;
import com.herusantoso.tokonezia.domain.Purchase;
import com.herusantoso.tokonezia.domain.Shop;
import com.herusantoso.tokonezia.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    Optional<Purchase> findOneBySecureId(String secureId);

    Page<Purchase> findByBuyerAndDeletedFalse(User buyer, Pageable pageable);

    Optional<Purchase> findOneByPayment(Payment payment);
}
