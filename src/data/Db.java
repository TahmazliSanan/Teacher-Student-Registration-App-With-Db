package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Db {
    public static Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306" +
                "/teacher_student_registration_app_db?user=root&password=23042002&characterEncoding=UTF-8");
    }
}
