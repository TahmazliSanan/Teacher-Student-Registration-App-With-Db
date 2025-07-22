package services;

import data.Db;
import entities.Teacher;
import repositories.BaseRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TeacherService implements BaseRepository<Teacher> {
    @Override
    public void insert(Teacher teacher) throws SQLException {
        PreparedStatement statement = Db.connect()
                .prepareStatement("insert into teachers (first_name, last_name) values (?, ?)");
        statement.setString(1, teacher.getFirstName());
        statement.setString(2, teacher.getLastName());
        statement.execute();
    }

    @Override
    public Teacher findById(Long id) throws SQLException {
        PreparedStatement statement = Db.connect().prepareStatement("select * from teachers where id = ?");
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            return new Teacher(id, resultSet.getString("first_name"), resultSet.getString("last_name"), null);
        }
        return null;
    }

    @Override
    public List<Teacher> findAll() throws SQLException {
        Statement statement = Db.connect().createStatement();
        ResultSet resultSet = statement.executeQuery("select * from teachers");
        List<Teacher> teachers = new ArrayList<>();

        while (resultSet.next()) {
            teachers.add(new Teacher(resultSet.getLong("id"), resultSet.getString("first_name"),
                    resultSet.getString("last_name"), null));
        }

        return teachers;
    }

    @Override
    public void update(Teacher teacher) throws SQLException {
        PreparedStatement statement = Db.connect()
                .prepareStatement("update teachers set first_name = ?, last_name = ? where id = ?");
        statement.setString(1, teacher.getFirstName());
        statement.setString(2, teacher.getLastName());
        statement.setLong(3, teacher.getId());
        statement.execute();
    }

    @Override
    public void delete(Teacher teacher) throws SQLException {
        PreparedStatement statement = Db.connect().prepareStatement("delete from teachers where id = ?");
        statement.setLong(1, teacher.getId());
        statement.execute();
    }

    public void registerSubjectToTeacher(Long teacherId, Long subjectId) throws SQLException {
        PreparedStatement statement = Db.connect()
                .prepareStatement("insert into teacher_subject (teacher_id, subject_id) values (?, ?)");
        statement.setLong(1, teacherId);
        statement.setLong(2, subjectId);
        statement.execute();
    }
}
