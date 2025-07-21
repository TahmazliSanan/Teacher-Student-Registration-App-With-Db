package services.menu;

import java.sql.SQLException;

public interface BaseMenuService {
    void create() throws SQLException;
    void getById() throws SQLException;
    void getList() throws SQLException;
    void update() throws SQLException;
    void delete() throws SQLException;
}
