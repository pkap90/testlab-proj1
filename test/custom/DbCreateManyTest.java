package hibernateonetomany;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import org.hibernate.Session;
import org.hibernate.Transaction;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/*
Adding many Employees do database
*/
@RunWith(Parameterized.class)
public class DbCreateManyTest {

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

    public DbCreateManyTest(String fname, String lname, int salary, Set set) {
        certificates = set;

        employee1 = new Employee(fname, lname, salary);
        employee1.setCertificates(certificates);

        employee2 = new Employee(fname, lname, salary);
        employee2.setCertificates(certificates);
    }

    @Test
    public void test() {
        Session session = SessionConfiguration.getSessionFactory().openSession();
        Transaction tx = null;

        List employeesDB = null;
        Integer setId1 = null;
        Integer setId2 = null;

        try {
            tx = session.beginTransaction();

            session.createQuery("DELETE FROM Employee"); // delete all records of Employee class in database  
            
            setId1 = (Integer) session.save(employee1);
            setId2 = (Integer) session.save(employee2);

            employeesDB = session.createQuery("FROM Employee E WHERE E.lastName='" + employee1.getLastName() + "'").list();

            tx.rollback();
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        }

        assertTrue(setId1 != null);          // check if employee is saved to DB (id is set)
        assertTrue(setId2 != null);          // check if employee is saved to DB (id is set)
        assertThat(setId1.intValue(), is(not(setId2.intValue())));
        
        assertTrue(employeesDB.size() > 0);     // check if employees exist in database
        assertTrue(employeesDB.size() > 1);     // check if there are more that one employee respecting the rule
    }
}
