package com.htxx.service;

import org.springframework.stereotype.Service;

import java.util.Set;


public interface RoleServiceI {
    Set<String> getRoleByUsername(String username);
}
