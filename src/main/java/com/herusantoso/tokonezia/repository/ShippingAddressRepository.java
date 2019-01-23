package com.herusantoso.tokonezia.repository;

import com.herusantoso.tokonezia.domain.ShippingAddress;
import com.herusantoso.tokonezia.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShippingAddressRepository extends JpaRepository<ShippingAddress, Long> {

    Optional<ShippingAddress> findOneBySecureId(String secureId);

    Page<ShippingAddress> findByBuyerAndDeletedFalse(User buyer, Pageable pageable);
}
