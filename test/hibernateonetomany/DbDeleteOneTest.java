package hibernateonetomany;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/*
 Test Reading one Employee from database
 */
@RunWith(Parameterized.class)
public class DbDeleteOneTest {

    private final Employee employee;
    private final Set certificates;

    @Parameterized.Parameters
    public static Collection<Object[]> employees() {
        return Arrays.asList(new Object[][]{
            {"Fryderyk", "Chopin", 7000, new HashSet(Arrays.asList(new Certificate("AAA"), new Certificate("BBB"), new Certificate("CCC")))},
            {"Ludwig", "van Beethoven", 1000, new HashSet(Arrays.asList(new Certificate("ABC"), new Certificate("DEF")))},
            {"Antonio", "Vivaldi", 5000, new HashSet(Arrays.asList(new Certificate("ABCDEF")))},
            {"Ryszard", "Wagner", 6000, new HashSet(Arrays.asList(new Certificate("AAA"), new Certificate("BBB"), new Certificate("CCC")))}, // {"Ryszard", "Strauss", 9000, null},
            {null, "Dvorak", 9000, new HashSet(Arrays.asList(new Certificate("ABCDEF")))},
            {"Maurice", null, 3500, new HashSet(Arrays.asList(new Certificate("ABCDEF")))},
            {null, "Ravel", 0, new HashSet(Arrays.asList(new Certificate("ABCDEF")))},
            {"Claude", "Debussy", -1000, new HashSet(Arrays.asList(new Certificate("ABCDEF")))} // UWAGA!
        });
    }

    public DbDeleteOneTest(String fname, String lname, int salary, Set set) {
        employee = new Employee(fname, lname, salary);
        certificates = set;
        employee.setCertificates(certificates);
    }

    @Test
    public void test() {
        Session session = SessionConfiguration.getSessionFactory().openSession();
        Transaction tx = null;

        List employees = null;
        Employee employeeDB = null;
        Integer id = null;

        try {
            tx = session.beginTransaction();

            id = (Integer) session.save(employee);
            employeeDB = (Employee) session.get(Employee.class, id);
            session.delete(employeeDB);

            employees = session.createQuery("FROM Employee").list();
            
            tx.rollback();
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        }

        assertFalse(employees.size() > 0);
        assertEquals(employees.size(),0);       
    }
}
