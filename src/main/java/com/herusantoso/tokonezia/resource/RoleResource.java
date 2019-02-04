package com.herusantoso.tokonezia.resource;

import com.herusantoso.tokonezia.dto.ResultDTO;
import com.herusantoso.tokonezia.dto.RoleDTO;
import com.herusantoso.tokonezia.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("api")
public class RoleResource {

    @Autowired
    private RoleService roleService;

    @PostMapping("/role")
    public ResponseEntity<ResultDTO> createRole(@Valid @RequestBody RoleDTO roleDTO) {
        RequestHandler handler = new RequestHandler() {
            @Override
            public Object processRequest() {
                return roleService.createRole(roleDTO);
            }
        };
        return handler.getResult();
    }


}
