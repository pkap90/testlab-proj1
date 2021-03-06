package hibernateonetomany;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/*
Read many objects from database based on Employee.salary
*/
@RunWith(Parameterized.class)
public class DbReadManyTest3 extends BaseDbTest{

    private final Employee employee1;
    private final Employee employee2;
    private final Set certificates;

    @Parameterized.Parameters
    public static Collection<Object[]> employees() {
        return Arrays.asList(new Object[][]{
            {"Fryderyk", "Chopin", 7000, new HashSet(Arrays.asList(new Certificate("AAA"), new Certificate("BELALA")))},
            {"Karol", "Szymanowski", 5000, new HashSet(Arrays.asList(new Certificate("ABCDEFSS")))}
        });
    }

    public DbReadManyTest3(String fname, String lname, int salary, Set set) {
        certificates = set;

        employee1 = new Employee(fname, lname, salary);
        employee1.setCertificates(certificates);

        employee2 = new Employee(fname, lname, salary);
        employee2.setCertificates(certificates);
    }

    @Test
    public void test() {
        List employeesDB = null;
        Integer setId1 = null;
        Integer setId2 = null;

        setId1 = (Integer) session.save(employee1);
        setId2 = (Integer) session.save(employee2);

        employeesDB = session.createQuery("FROM Employee E WHERE E.salary=" + employee1.getSalary()).list();

        assertTrue(setId1 != null);          // check if employee is saved to DB (id is set)
        assertTrue(setId2 != null);          // check if employee is saved to DB (id is set)
        assertThat(setId1.intValue(), is(not(setId2.intValue())));

        assertTrue(employeesDB.size() > 0);     // check if employee exists in database      
        assertTrue(employeesDB.size() > 1);     // check if there are more that one employee respecting the rule

        assertThat(((Employee) employeesDB.get(0)).getId(), is(not(((Employee) employeesDB.get(1)).getId())));
        assertEquals(((Employee) employeesDB.get(0)).getSalary(), ((Employee) employeesDB.get(1)).getSalary());

        // check certificates
        assertTrue(!((Employee) employeesDB.get(0)).getCertificates().isEmpty()); // check if we have any certificates
        assertTrue(!((Employee) employeesDB.get(1)).getCertificates().isEmpty()); // check if we have any certificates
    }
}
