package com.Service.Impl;

import com.Service.CommodityService;
import com.dao.CommodityDao;
import com.pojo.Commodity;
import com.pojo.Vo.CommodityVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@EnableCaching
public class CommodityImpl implements CommodityService {
    List<CommodityVo> list=new ArrayList<>();
    @Autowired
    CommodityDao commodityDao;

    @Override
    public Page<Commodity> queryByName(String name, Integer pageNum, Integer pageSize) {
        Sort sort=Sort.by(Sort.Direction.ASC,"id");
        Pageable pageable=PageRequest.of(pageNum,pageSize,sort);
        Page<Commodity> list=commodityDao.QueryByName(name,pageable);
        return list.getContent().size() > 0 ? list : null;
    }

    @Override
    public Page<Commodity> findByPriceBetween(Integer minPrice, Integer maxPrice,String sorts, Integer pageNum, Integer pageSize) {
        Sort sort=Sort.by(Sort.Direction.fromString(sorts),"price");
        Pageable pageable=PageRequest.of(pageNum, pageSize,sort);
        if (minPrice == null){
           minPrice = 0;
        }
        if (maxPrice == null){
            maxPrice = 99999;
        }
        Page<Commodity> list=commodityDao.findByPriceBetween(minPrice, maxPrice, pageable);
        return list.getContent().size() > 0 ? list : null;
    }
}
