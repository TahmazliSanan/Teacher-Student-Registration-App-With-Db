package services;

import data.Db;
import entities.Subject;
import repositories.BaseRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SubjectService implements BaseRepository<Subject> {
    @Override
    public void insert(Subject subject) throws SQLException {
        PreparedStatement statement = Db.connect().prepareStatement("insert into subjects (name) set values (?)");
        statement.setString(1, subject.getName());
        statement.execute();
    }

    @Override
    public Subject findById(Long id) throws SQLException {
        PreparedStatement statement = Db.connect().prepareStatement("select * from subjects where id = ?");
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            return new Subject(id, resultSet.getString("name"));
        }
        return null;
    }

    @Override
    public List<Subject> findAll() throws SQLException {
        Statement statement = Db.connect().createStatement();
        ResultSet resultSet = statement.executeQuery("select * from subjects");
        List<Subject> subjects = new ArrayList<>();

        while (resultSet.next()) {
            subjects.add(new Subject(resultSet.getLong("id"), resultSet.getString("name")));
        }

        return subjects;
    }

    @Override
    public void update(Subject subject) throws SQLException {
        PreparedStatement statement = Db.connect().prepareStatement("update subjects set name = ? where id = ?");
        statement.setString(1, subject.getName());
        statement.setLong(2, subject.getId());
        statement.execute();
    }

    @Override
    public void delete(Subject subject) throws SQLException {
        PreparedStatement statement = Db.connect().prepareStatement("delete from subjects where id = ?");
        statement.setLong(1, subject.getId());
        statement.execute();
    }
}
