package com.pojo.Vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindByPriceVo implements Serializable {
    private Integer minPrice;
    private Integer maxPrice;
    private Integer pageNum;
    private Integer pageSize;
    private String sort;
}
