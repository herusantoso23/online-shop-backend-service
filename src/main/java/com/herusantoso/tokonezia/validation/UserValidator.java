package com.herusantoso.tokonezia.validation;

import com.herusantoso.tokonezia.domain.User;
import com.herusantoso.tokonezia.dto.UserCreateDTO;
import com.herusantoso.tokonezia.exception.BadRequestException;
import com.herusantoso.tokonezia.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserValidator {

    @Autowired
    UserRepository userRepository;

    public void createValidation(UserCreateDTO dto){
        Optional<User> emailFound = userRepository.findOneByEmailIgnoreCase(dto.getEmail());
        if(emailFound.isPresent()){
            throw new BadRequestException("Email already used");
        }

        Optional<User> usernameFound = userRepository.findOneByUsername(dto.getUsername());
        if(usernameFound.isPresent()){
            throw new BadRequestException("Username already used");
        }
    }
}
