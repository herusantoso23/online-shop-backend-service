package com.herusantoso.tokonezia.security;

import com.herusantoso.tokonezia.domain.User;
import com.herusantoso.tokonezia.exception.BadRequestException;
import com.herusantoso.tokonezia.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacade {

    @Autowired
    private UserRepository userRepository;

    public User getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return userRepository.findOneByUsername(authentication.getName())
                .orElseThrow(() -> new BadRequestException("User not found"));
    }

}
