package hibernateonetomany;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/*
 Test Reading one Employee from database
 */
@RunWith(Parameterized.class)
public class DbReadOneTest extends BaseDbTest{

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

    public DbReadOneTest(String fname, String lname, int salary, Set set) {
        employee = new Employee(fname, lname, salary);
        certificates = set;
        employee.setCertificates(certificates);
    }

    @Test
    public void test() {
        Employee employeeDB = null;
        Integer id = null;

        id = (Integer) session.save(employee);
        employeeDB = (Employee) session.get(Employee.class, id);

        assertTrue(id != null);                             // if employee is in database then ID is set
        assertTrue(employeeDB != null);                     // check if employee really exists in database

        // READ values - check for Equality
        assertEquals(id.intValue(), employeeDB.getId());

        assertEquals(employee, employeeDB);
        assertEquals(employee.getFirstName(), employeeDB.getFirstName());
        assertEquals(employee.getLastName(), employeeDB.getLastName());
        assertEquals(employee.getSalary(), employeeDB.getSalary());

        assertTrue(!employee.getCertificates().isEmpty());   // check if we have any certificates
        assertThat(employee.getCertificates().toArray(), is(equalTo(employeeDB.getCertificates().toArray())));
    }
}
