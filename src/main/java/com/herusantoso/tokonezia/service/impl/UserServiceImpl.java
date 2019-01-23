package com.herusantoso.tokonezia.service.impl;

import com.herusantoso.tokonezia.domain.Role;
import com.herusantoso.tokonezia.domain.User;
import com.herusantoso.tokonezia.dto.ChangePasswordDTO;
import com.herusantoso.tokonezia.dto.UserCreateDTO;
import com.herusantoso.tokonezia.dto.UserDetailDTO;
import com.herusantoso.tokonezia.dto.UserUpdateDTO;
import com.herusantoso.tokonezia.exception.BadRequestException;
import com.herusantoso.tokonezia.mapper.UserMapper;
import com.herusantoso.tokonezia.repository.RoleRepository;
import com.herusantoso.tokonezia.repository.UserRepository;
import com.herusantoso.tokonezia.security.AuthenticationFacade;
import com.herusantoso.tokonezia.service.UserService;
import com.herusantoso.tokonezia.util.Constants;
import com.herusantoso.tokonezia.validation.UserValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private AuthenticationFacade authenticationFacade;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Boolean createUser(UserCreateDTO userDTO) {
        userValidator.createValidation(userDTO);

        User user = UserMapper.INSTANCE.toEntity(userDTO, new User());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findByName(Constants.Role.USER)
                .orElseThrow(() -> new BadRequestException("Role not found")));
        user.setRoles(roles);
        userRepository.save(user);

        return Boolean.TRUE;
    }

    @Override
    public Boolean updateUser(UserUpdateDTO userDTO) {
        User user = authenticationFacade.getCurrentUser();

        user = UserMapper.INSTANCE.toEntity(userDTO, user);
        userRepository.save(user);
        return Boolean.TRUE;
    }

    @Override
    public Boolean changePassword(ChangePasswordDTO changePasswordDTO){
        User user = authenticationFacade.getCurrentUser();

        if(passwordEncoder.matches(changePasswordDTO.getOldPassword(), user.getPassword())){
            if(changePasswordDTO.getNewPassord().equals(changePasswordDTO.getConfirmPassword())){
                user.setPassword(passwordEncoder.encode(changePasswordDTO.getConfirmPassword()));
                userRepository.save(user);
            } else {
                throw new BadRequestException("Confirm password doesnt match");
            }
        } else {
            throw new BadRequestException("Old password invalid");
        }
        return Boolean.TRUE;
    }

    @Override
    public UserDetailDTO getDetail(){
        User user = authenticationFacade.getCurrentUser();
        return UserMapper.INSTANCE.toDTO(user, new UserDetailDTO());
    }


}
