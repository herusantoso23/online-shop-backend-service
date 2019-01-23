package com.herusantoso.tokonezia.service;

import com.herusantoso.tokonezia.dto.ChangePasswordDTO;
import com.herusantoso.tokonezia.dto.UserCreateDTO;
import com.herusantoso.tokonezia.dto.UserDetailDTO;
import com.herusantoso.tokonezia.dto.UserUpdateDTO;

public interface UserService {

    Boolean createUser(UserCreateDTO userDTO);

    Boolean updateUser(UserUpdateDTO userDTO);

    Boolean changePassword(ChangePasswordDTO changePasswordDTO);

    UserDetailDTO getDetail();
}
