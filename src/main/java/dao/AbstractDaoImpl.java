package dao;

import databaseSettings.DBConnector;
import databaseSettings.DatabaseSelect;
import model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AbstractDaoImpl implements DaoI {
    private static final AbstractDao INSTANCE = new AbstractDaoImpl();
    private static final String SQL_SELECT_ALL = "SELECT * FROM STUDENTS";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM STUDENTS WHERE id=?";
    private static final String SQL_DELETE_BY_ID = "DELETE FROM STUDENTS WHERE id=?";
    private static final String SQL_FIND_BY_ID = "SELECT FROM STUDENTS WHERE id=?";
    private static final String SQL_CREATE_STUDENT = "INSERT INTO STUDENTS (id, first_name, last_name, age, city) VALUES ";


    private AbstractDaoImpl() {
    }

    public static AbstractDao getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Student> findAll() {
        List<Student> students = new ArrayList<>();
        try {
            Connection connection = DBConnector.getConnection(DatabaseSelect.h2);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SQL_SELECT_ALL);
            while (rs.next()) {
                Student student = new Student(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getInt("age"),
                        rs.getString("city")
                );
                students.add(student);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return students;
    }


    @Override
    public Student read(Integer id) {
        Student student = null;
        try (Connection connection = DBConnector.getConnection(DatabaseSelect.h2);
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                student = new Student(
                        id,
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getInt("age"),
                        rs.getString("city")
                );
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return student;
    }

    @Override
    public Student update(Student student, Integer id, String name, String last_name, Integer age, String city) {

        return null;
    }

    @Override
    public Student create(Integer id, String name, String last_name, Integer age, String city) {

        return null;
    }


    @Override
    public void delete(Integer id) {
        try (Connection connection = DBConnector.getConnection(DatabaseSelect.h2);
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE_BY_ID)) {

            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
