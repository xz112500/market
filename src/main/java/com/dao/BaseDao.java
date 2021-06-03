package com.dao;


public interface BaseDao<T>{
     int delete(int id);

     int update(int id,Class<?> entityClass);

    <S extends T> S save(S entity);

    <S extends T> S update(S s);
}
