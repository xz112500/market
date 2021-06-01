package com.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "commodity")
@Component
@Entity
@ApiModel(description = "商品实体类")
public class Commodity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "主键id")
    private int id;
    @Column(name = "numbers")
    @ApiModelProperty(value = "编号")
    private int numbers;
    @Column(name = "name")
    @ApiModelProperty(value = "商品名称")
    private String name;
    @Column(name = "price")
    @ApiModelProperty(value = "商品价格")
    private int price;
    @Column(name = "counts")
    @ApiModelProperty(value = "商品总数")
    private int counts;
    @Column(name = "img")
    @ApiModelProperty(value = "图片链接")
    private String img;
    @Column(name = "create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "创建日期")
    private Date create_time;
}
