package com.htxx.service.impl;

import com.htxx.mapper.PermissionMapper;
import com.htxx.service.PermissionServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PermissionServiceImpl implements PermissionServiceI {

    @Autowired
    PermissionMapper permissionMapper;
    @Override
    public Set<String> getPermissionByUsername(String username) {
        return permissionMapper.getPermissionByUsername(username);
    }
}
