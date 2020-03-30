package com.htxx.service.impl;

import com.htxx.mapper.RoleMapper;
import com.htxx.service.RoleServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RoleServiceImpl implements RoleServiceI {
    @Autowired
    RoleMapper roleMapper;
    @Override
    public Set<String> getRoleByUsername(String username) {
        return roleMapper.getRoleByUsername(username);
    }
}
