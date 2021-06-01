package com.pojo.Vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVo implements Serializable {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private Integer status;
    private String token;

    public UserVo(Integer id, String username, String email, Integer status, String token) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.status = status;
        this.token = token;
    }
}
