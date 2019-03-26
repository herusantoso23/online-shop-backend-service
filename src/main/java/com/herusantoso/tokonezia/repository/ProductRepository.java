package com.herusantoso.tokonezia.repository;


import com.herusantoso.tokonezia.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findOneBySecureId(String secureId);

    Page<Product> findByNameLikeIgnoreCaseAndCategoryLikeIgnoreCaseAndDeletedFalse(String productName, String category, Pageable pageable);
	
}
