package sevice;

import dao.AbstractDao;
import dao.AbstractDaoImpl;
import model.Student;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StudentDaoImpl implements StudentDao {
    private static final StudentDao INSTANCE = new StudentDaoImpl();
    private final AbstractDao abstractDao = AbstractDaoImpl.getInstance();

    private StudentDaoImpl() {
    }

    public static StudentDao getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Student> findAll() {
        return abstractDao.findAll();
    }

    public void delete(int id) {
        abstractDao.delete(id);
    }

    @Override
    public void create(Student entity) {

    }

    @Override
    public Student findById(int id) {
        return null;
    }

    @Override
    public Student update(Student entity) {
        return null;
    }

    @Override
    public List<Student> findAllSortedByAge() {
        return findAll().stream()
                .sorted(Comparator.comparing(Student::getAge))
                .collect(Collectors.toList());
    }

    @Override
    public List<Student> filterByName(String prefix) {
        return findAll().stream()
                .filter(st -> st.getFirstName()
                        .startsWith(prefix))
                .collect(Collectors.toList());
    }

    @Override
    public List<Student> filterDeleteAgeBetween(int startAge, int endAge) {
        List<Student> st = findAll();
        for (int i = 0; i < st.size(); i++) {
            if (st.get(i).getAge() > startAge && st.get(i).getAge() < endAge) {
                st.remove(i);
            }
        }
        return st;
    }
}
