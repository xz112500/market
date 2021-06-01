package com.pojo.Vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageQuery implements Serializable {
    private Integer pageNum;
    private Integer pageSize;
    private String username;
}
