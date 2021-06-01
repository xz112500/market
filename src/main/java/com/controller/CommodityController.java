package com.controller;


import com.Service.CommodityService;
import com.pojo.Commodity;
import com.pojo.Vo.FindByPriceVo;
import com.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/Commodity")
public class CommodityController {
    ResultUtil<Object> resultUtil=new ResultUtil<>();
    @Autowired
    CommodityService service;
    @GetMapping("/queryByName")
    public ResultUtil<Object> QueryByName(@RequestParam String name, @RequestParam(value = "pageNum",defaultValue = "1",required = false) Integer pageNum,@RequestParam(value = "pageSize",defaultValue = "5",required = false) Integer pageSize){
        return resultUtil.ok(service.queryByName(name, pageNum-1, pageSize));
    }
    @GetMapping("/queryByPrice")
    public ResultUtil<Object> QueryByPrice(@ModelAttribute FindByPriceVo findByPriceVo){
        Page<Commodity> page=service.findByPriceBetween(findByPriceVo.getMinPrice(), findByPriceVo.getMaxPrice(),findByPriceVo.getSort(),
                findByPriceVo.getPageNum()-1,findByPriceVo.getPageSize() );
         /**
         *   流过滤
         *   List<Commodity> collect = page.stream().filter(p -> p.getPrice() > 10000).collect(Collectors.toList());
         */

        return page != null ? resultUtil.ok(page) : resultUtil.ok(service.queryByName("",1,5)).status(201).message("查找失败!");
    }
}
