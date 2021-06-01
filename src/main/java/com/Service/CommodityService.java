package com.Service;

import com.pojo.Commodity;
import org.springframework.data.domain.Page;

public interface CommodityService {
   Page<Commodity> queryByName(String name, Integer pageNum, Integer pageSize);

   Page<Commodity> findByPriceBetween(Integer minPrice, Integer maxPrice,String sorts, Integer pageNum,Integer pageSize);
}
