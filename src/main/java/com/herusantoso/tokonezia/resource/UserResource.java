package com.herusantoso.tokonezia.resource;

import com.herusantoso.tokonezia.domain.User;
import com.herusantoso.tokonezia.dto.ChangePasswordDTO;
import com.herusantoso.tokonezia.dto.ResultDTO;
import com.herusantoso.tokonezia.dto.UserCreateDTO;
import com.herusantoso.tokonezia.dto.UserUpdateDTO;
import com.herusantoso.tokonezia.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("api/v1")
public class UserResource {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity<ResultDTO> createUser(@Valid @RequestBody UserCreateDTO userDTO) {
        RequestHandler handler = new RequestHandler() {
            @Override
            public Object processRequest() {
                return userService.createUser(userDTO);
            }
        };
        return handler.getResult();
    }

    @PreAuthorize("isAuthenticated()")
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("/user")
    public ResponseEntity<ResultDTO> updateUser(@Valid @RequestBody UserUpdateDTO userDTO) {
        RequestHandler handler = new RequestHandler() {
            @Override
            public Object processRequest() {
                return userService.updateUser(userDTO);
            }
        };
        return handler.getResult();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/user/profile")
    public ResponseEntity<ResultDTO> getDetailUser() {
        RequestHandler handler = new RequestHandler() {
            @Override
            public Object processRequest() {
                return userService.getDetail();
            }
        };
        return handler.getResult();
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/user/change-password")
    public ResponseEntity<ResultDTO> changePassword(@Valid @RequestBody ChangePasswordDTO userDTO) {
        RequestHandler handler = new RequestHandler() {
            @Override
            public Object processRequest() {
                return userService.changePassword(userDTO);
            }
        };
        return handler.getResult();
    }

}
