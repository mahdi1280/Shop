package ir.maktab.shop.repository;

import java.sql.SQLException;
import java.util.List;

public interface BaseRepository<T> {

    int save(T t) throws SQLException;

    void update(T t) throws SQLException;

    List<T> findAll() throws SQLException;

    void delete(int id) throws SQLException;

    T findById(int id) throws SQLException;
}
