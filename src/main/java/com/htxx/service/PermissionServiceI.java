package com.htxx.service;

import org.springframework.stereotype.Service;

import java.util.Set;

public interface PermissionServiceI {
    Set<String> getPermissionByUsername(String username);
}
