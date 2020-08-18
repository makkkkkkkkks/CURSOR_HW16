package sevice;

import model.Student;

import java.util.List;

public interface StudentDao {
    List<Student> findAll();

    Student findById(int id);

    void delete(int id);

    void create(Student entity);

    Student update(Student entity);

    List<Student> findAllSortedByAge();

    List<Student> filterByName(String prefix);

    List<Student> filterDeleteAgeBetween(int startAge, int endAge);
}
