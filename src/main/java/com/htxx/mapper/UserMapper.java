package com.htxx.mapper;

import com.htxx.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> selectAll();
    String selectNameById(String id);
    int login(User u);
}
