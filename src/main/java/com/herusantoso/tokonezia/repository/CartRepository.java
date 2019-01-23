package com.herusantoso.tokonezia.repository;


import com.herusantoso.tokonezia.domain.Cart;
import com.herusantoso.tokonezia.domain.Product;
import com.herusantoso.tokonezia.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findOneBySecureId(String secureId);

    Page<Cart> findByBuyerAndPurchaseFalseAndDeletedFalse(User buyer, Pageable pageable);

    Optional<Cart> findOneByProductSecureIdAndBuyer(Product product, User buyer);
}
