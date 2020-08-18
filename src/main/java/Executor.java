import databaseSettings.DBConnector;
import databaseSettings.DatabaseSelect;
import model.Student;
import sevice.StudentDao;
import sevice.StudentDaoImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Executor {
    private static final StudentDao STUDENT_DAO = StudentDaoImpl.getInstance();

    public static void start() {
        addData();
        printAllStudents();
        sortedByAge();
        filterByName();
        deleteBetweenAge(20, 60);

    }

    private static void printAllStudents() {
        System.out.println("___________________________All students____________________");
        List<Student> students = STUDENT_DAO.findAll();
        students.forEach(student -> System.out.println("||" + student));
        System.out.println("___________________________________________________________");
    }

    private static void sortedByAge() {
        System.out.println("___________________________Sorted By Age___________________");
        List<Student> students = STUDENT_DAO.findAllSortedByAge();
        students.forEach(student -> System.out.println("||" + student));
        System.out.println("___________________________________________________________");
    }

    private static void filterByName() {
        System.out.println("______________________Names start from J___________________");
        List<Student> students = STUDENT_DAO.filterByName("J");
        students.forEach(student -> System.out.println("||" + student));
        System.out.println("___________________________________________________________");
    }

    private static void deleteBetweenAge(int start, int end) {
        System.out.println("________________Delete students between age________________");

        List<Student> students = STUDENT_DAO.filterDeleteAgeBetween(start, end);
        students.forEach(student -> System.out.println("||" + student));
        System.out.println("___________________________________________________________");
    }

    private static void addData() {
        try {
            Connection connection = DBConnector.getConnection(DatabaseSelect.h2);
            String sqlCreateTable = "CREATE TABLE STUDENTS "
                    + "(id INTEGER not NULL, "
                    + " first_name VARCHAR(255), "
                    + " last_name VARCHAR(255), "
                    + " age INTEGER, "
                    + " city VARCHAR(255), "
                    + " PRIMARY KEY ( id ))";
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlCreateTable);
            String sqlInsertDataToTable = "INSERT INTO STUDENTS (id, first_name, last_name, age, city) VALUES "
                    + "(1, 'Roman', 'Romanenko', 60, 'Kyiv'),"
                    + "(2, 'Maks' , 'Maksumenko', 18, 'Odesa'),"
                    + "(3, 'Larusa', 'Ivanuk', 23, 'Kharkov'),"
                    + "(4, 'Natasha', 'Stepanuk', 90, 'Lvov'),"
                    + "(5, 'Oleg', 'Butulskiy', 19, 'Cherkassu'),"
                    + "(6, 'Igor', 'Kyznetsov', 29, 'Chernigov'),"
                    + "(7, 'Ivan', 'Goloborodko', 39, 'Ivano-Frankivsk'),"
                    + "(8, 'Jeykob', 'Smit', 49, 'Usggorod'),"
                    + "(9, 'Maria', 'вуьфтнл', 60, 'Smila');";
            statement.executeUpdate(sqlInsertDataToTable);
            statement.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
