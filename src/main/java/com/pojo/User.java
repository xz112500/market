package com.pojo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Component
@ConfigurationProperties(prefix = "spring.redis")
@Table(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "User实体类")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "主键id")
    private Integer id;
    @Column(name = "username")
    @ApiModelProperty(value = "用户名")
    private String username;
    @Column(name = "password")
    @ApiModelProperty(value = "密码")
    private String password;
    @Column(name = "token")
    @ApiModelProperty(value = "token")
    private String token;
    @Column(name = "email")
    @ApiModelProperty(value = "邮箱")
    private String email;
    @Column(name = "status")
    @ApiModelProperty(value = "状态")
    private Integer status;
}
