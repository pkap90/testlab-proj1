package custom;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
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
 Test Updating an Employee from database
 */
@RunWith(Parameterized.class)
public class DbUpdateTest {

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

    public DbUpdateTest(String fname, String lname, int salary, Set set) {
        employee = new Employee(fname, lname, salary);
        certificates = set;
        employee.setCertificates(certificates);
    }

    @Test
    public void test() {
        Session session = SessionConfiguration.getSessionFactory().openSession();
        Transaction tx = null;

        Employee employeeDB = null;
        Employee employeeUpdatedDB = null;
        Integer id = null;

        try {
            tx = session.beginTransaction();

            session.createQuery("DELETE FROM Employee"); // delete all records of Employee class in database
            
            id = (Integer) session.save(employee);
            employeeDB = (Employee) session.get(Employee.class, id);

            employeeDB.setFirstName("Jan");
            employeeDB.setLastName("Kowalski");
            employeeDB.setSalary(999);
            employeeDB.setCertificates(new HashSet(Arrays.asList(new Certificate("HelloWorld"))));
            session.update(employeeDB);

            employeeUpdatedDB = (Employee) session.get(Employee.class, id);

            tx.rollback();
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        }

        assertTrue(id != null);                             // if employee is in database then ID is set
        assertTrue(employeeDB != null);                     // check if employee really exists in database
        assertTrue(employeeUpdatedDB != null);                     // check if employee really exists in database

        // READ values - check for Equality
        assertEquals(id.intValue(), employeeDB.getId());
        assertEquals(employeeDB.getId(), employeeUpdatedDB.getId());

        assertEquals(employeeUpdatedDB.getFirstName(), "Jan"); // the same object in database
        assertEquals(employeeUpdatedDB.getLastName(), "Kowalski");
        assertEquals(employeeUpdatedDB.getSalary(), 999);
        assertEquals(employeeUpdatedDB.getFirstName(), "Jan");
        assertThat(employeeDB.getCertificates().toArray(), is(equalTo(employeeUpdatedDB.getCertificates().toArray())));
    }
}
