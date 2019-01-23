package com.herusantoso.tokonezia.repository;

import com.herusantoso.tokonezia.domain.Shop;
import com.herusantoso.tokonezia.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {

    Optional<Shop> findOneBySecureId(String secureId);

    Page<Shop> findBySellerAndDeletedFalse(User seller, Pageable pageable);

}
