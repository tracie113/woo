package com.htxx.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class User implements Serializable {
    private String id;
    private String loginname;
    private String username;
    private String password;

}
