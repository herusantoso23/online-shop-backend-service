package com.herusantoso.tokonezia.security;

import com.herusantoso.tokonezia.util.Constants;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Implementation of AuditorAware based on Spring Security.
 */
@Component
@EnableJpaAuditing
public class SpringSecurityAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        try {
            return Optional.of(SecurityContextHolder.getContext().getAuthentication().getName());
        } catch (Exception e){
            return Optional.of(Constants.User.SYSTEM_ACCOUNT);
        }
//        return Optional.of(Constants.User.SYSTEM_ACCOUNT);
    }

}
