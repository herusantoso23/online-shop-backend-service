package com.herusantoso.tokonezia.security;

import com.herusantoso.tokonezia.domain.User;
import com.herusantoso.tokonezia.exception.BadRequestException;
import com.herusantoso.tokonezia.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service(value = "userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String input) {
        User user = null;

        if (input.contains("@"))
            user = userRepository.findOneByEmailIgnoreCase(input)
                    .orElseThrow(() -> new BadRequestException("Bad credentials"));
        else
            user = userRepository.findOneByUsername(input)
                    .orElseThrow(() -> new BadCredentialsException("Bad credentials"));

        new AccountStatusUserDetailsChecker().check(user);

        return user;
    }

}
