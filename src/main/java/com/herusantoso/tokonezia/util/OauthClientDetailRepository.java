package com.herusantoso.tokonezia.util;

import com.herusantoso.tokonezia.domain.OauthClientDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OauthClientDetailRepository extends JpaRepository<OauthClientDetail, String> {
}
