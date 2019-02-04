package com.herusantoso.tokonezia.service.impl;

import com.herusantoso.tokonezia.domain.Role;
import com.herusantoso.tokonezia.dto.RoleDTO;
import com.herusantoso.tokonezia.repository.RoleRepository;
import com.herusantoso.tokonezia.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Boolean createRole(RoleDTO roleDTO) {
        Role role = new Role();
        role.setName(roleDTO.getName());
        roleRepository.save(role);
        return Boolean.TRUE;
    }
}
