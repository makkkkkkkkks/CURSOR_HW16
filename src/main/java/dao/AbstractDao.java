package dao;

import model.Student;

import java.util.List;

public interface AbstractDao<T, P> {
    List<Student> findAll();

    P create(Integer id, String name, String last_name, Integer age, String city);

    P read(T id);

    P update(P student, Integer id, String name, String last_name, Integer age, String city);

    void delete(T id);
}