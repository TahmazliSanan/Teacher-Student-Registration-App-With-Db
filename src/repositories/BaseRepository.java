package repositories;

import java.sql.SQLException;
import java.util.List;

public interface BaseRepository<T> {
    void insert(T t) throws SQLException;
    T findById(Long id) throws SQLException;
    List<T> findAll() throws SQLException;
    void update(T t) throws SQLException;
    void delete(T t) throws SQLException;
}
