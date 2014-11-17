package hibernateonetomany;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import org.hibernate.Session;
import org.hibernate.Transaction;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/*
 Add Employees to database that exceed limits of the database and therefore cannot be added
 */
@RunWith(Parameterized.class)
public class DbCreateOneWrongTest {

    private final Employee employee;
    private final Set certificates;

    @Rule
    public ExpectedException exception = ExpectedException.none();
    
    @Parameters
    public static Collection<Object[]> employees() {
        return Arrays.asList(new Object[][]{
            {"123456789012345678901", "Chopin", 7000, new HashSet(Arrays.asList(new Certificate("AAA")))}, // first_name > 20
            {"Ludwig", "123456789012345678901", 1000, new HashSet(Arrays.asList(new Certificate("ABC"), new Certificate("DEF")))}, // last_name > 20
            {"123456789012345678901", "123456789012345678901", 0, new HashSet(Arrays.asList(new Certificate("AAA")))} // first_name & last_name > 20
        });
    }

    public DbCreateOneWrongTest(String fname, String lname, int salary, Set set) {
        employee = new Employee(fname, lname, salary);
        certificates = set;
        employee.setCertificates(certificates);
    }

    @Test
    public void test() {
        Session session = SessionConfiguration.getSessionFactory().openSession();
        Transaction tx = null;

        Employee employeeDB = null;
        Integer setId = null;

        exception.expect(org.hibernate.exception.DataException.class);
        
        try {
            tx = session.beginTransaction();

            session.createQuery("DELETE FROM Employee");

            setId = (Integer) session.save(employee);
            employeeDB = (Employee) session.get(Employee.class, setId);

            tx.rollback();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        }

        assertTrue(setId == null);          // if employee is NOT in database (then ID is NOT set)
        assertTrue(employeeDB == null);     // check if employee wasn't written to database
        assertThat(employee, is(not(employeeDB)));
    }
}
