package services;

import data.Db;
import entities.Student;
import repositories.BaseRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentService implements BaseRepository<Student> {
    @Override
    public void insert(Student student) throws SQLException {
        PreparedStatement statement = Db.connect()
                .prepareStatement("insert into students (first_name, last_name, gpa, teacher_id) values (?, ?, ?, ?)");
        statement.setString(1, student.getFirstName());
        statement.setString(2, student.getLastName());
        statement.setDouble(3, student.getGpa());
        statement.setLong(4, student.getTeacherId());
        statement.execute();
    }

    @Override
    public Student findById(Long id) throws SQLException {
        PreparedStatement statement = Db.connect().prepareStatement("select * from students where id = ?");
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            return new Student(id, resultSet.getString("first_name"), resultSet.getString("last_name"),
                    resultSet.getDouble("gpa"), resultSet.getLong("teacher_id"));
        }
        return null;
    }

    @Override
    public List<Student> findAll() throws SQLException {
        Statement statement = Db.connect().createStatement();
        ResultSet resultSet = statement.executeQuery("select * from students");
        List<Student> students = new ArrayList<>();

        while (resultSet.next()) {
            students.add(new Student(resultSet.getLong("id"), resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getDouble("gpa"), resultSet.getLong("teacher_id")));
        }

        return students;
    }

    @Override
    public void update(Student student) throws SQLException {
        PreparedStatement statement = Db.connect()
                .prepareStatement("update students set first_name = ?, last_name = ?, gpa = ? where id = ?");
        statement.setString(1, student.getFirstName());
        statement.setString(2, student.getLastName());
        statement.setDouble(3, student.getGpa());
        statement.setLong(4, student.getId());
        statement.execute();
    }

    @Override
    public void delete(Student student) throws SQLException {
        PreparedStatement statement = Db.connect().prepareStatement("delete from students where id = ?");
        statement.setLong(1, student.getId());
        statement.execute();
    }
}
