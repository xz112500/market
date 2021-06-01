package com.dao;

import com.pojo.Commodity;
import com.pojo.Vo.CommodityVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommodityDao extends JpaRepository<Commodity,Integer> {
 /*
 * Integer实体类的id属性
 */
     @Query(value = "select * from User.commodity where name like concat('%',?,'%')",nativeQuery = true)
     Page<Commodity> QueryByName(String name, Pageable pageable);

     Page<Commodity> findByPriceBetween(Integer minPrice, Integer maxPrice, Pageable pageable);
}
