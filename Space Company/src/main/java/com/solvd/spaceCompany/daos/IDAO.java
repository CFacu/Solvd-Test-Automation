package com.solvd.spaceCompany.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface IDAO<T> {
    T get (Long id);
    T extractFromResultSet(ResultSet resultSet) throws SQLException;
    List<T> getAll();
    void insert (T t);
    void update(T t, Long id);
    void delete(Long id);
}