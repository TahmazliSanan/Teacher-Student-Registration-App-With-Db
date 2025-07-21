package services.menu;

import java.sql.SQLException;

public interface BaseMenuService {
    void create() throws SQLException;
    void getByPin();
    void getList();
    void update();
    void delete();
}
