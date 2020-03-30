package com.htxx.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.Set;

@Mapper
public interface RoleMapper {
    Set<String> getRoleByUsername(String username);
}
