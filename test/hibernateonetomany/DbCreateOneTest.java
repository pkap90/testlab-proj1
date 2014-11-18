package hibernateonetomany;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/*
 Test Adding one Emplyee to database 
 */
@RunWith(Parameterized.class)
public class DbCreateOneTest extends BaseDbTest{

    private final Employee employee;
    private final Set certificates;

    @Parameters
    public static Collection<Object[]> employees() {
        return Arrays.asList(new Object[][]{
            {"Fryderyk", "Chopin", 7000, new HashSet(Arrays.asList(new Certificate("AAA"), new Certificate("BBB"), new Certificate("CCC")))},
            {"Ludwig", "van Beethoven", 1000, new HashSet(Arrays.asList(new Certificate("ABC"), new Certificate("DEF")))},
            {"Antonio", "Vivaldi", 5000, new HashSet(Arrays.asList(new Certificate("ABCDEF")))},
            {"Ryszard", "Wagner", 6000, new HashSet(Arrays.asList(new Certificate("AAA"), new Certificate("BBB"), new Certificate("CCC")))},
            {null, "Dvorak", 9000, new HashSet(Arrays.asList(new Certificate("ABCDEF")))},
            {"Maurice", null, 3500, new HashSet(Arrays.asList(new Certificate("ABCDEF")))},
            {null, "Ravel", 0, new HashSet(Arrays.asList(new Certificate("ABCDEF")))},
            {"Claude", "Debussy", -1000, new HashSet(Arrays.asList(new Certificate("ABCDEF")))} // UWAGA! -1000
        });
    }

    public DbCreateOneTest(String fname, String lname, int salary, Set set) {
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

        assertTrue(id != null);          // if employee is in database then ID is set
        assertTrue(employeeDB != null);  // check if employee really exists in database
    }
}
