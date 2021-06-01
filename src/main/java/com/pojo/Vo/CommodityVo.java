package com.pojo.Vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommodityVo implements Serializable {
    private int id;
    private int numbers;
    private String name;
    private int price;
    private int counts;
    private String img;
}
