package com.solvd.spaceCompany.daos;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Mapper
public interface IDAO<T> {
    T get (@Param("id") Long id);
    T extractFromResultSet(ResultSet resultSet) throws SQLException;
    List<T> getAll();
    void insert (@Param("obj") T t);
    void update(@Param("obj") T t,@Param("id") Long id);
    void delete(@Param("id") Long id);
}