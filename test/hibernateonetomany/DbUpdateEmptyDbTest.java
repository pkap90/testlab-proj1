package hibernateonetomany;

import java.util.Arrays;
import java.util.HashSet;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

/*
 Test Reading Employee from empty database 
 */
public class DbUpdateEmptyDbTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();
    
    @Test
    public void test() {
        Session session = SessionConfiguration.getSessionFactory().openSession();
        Transaction tx = null;

        Employee employeeDB = null;
        
        exception.expect(NullPointerException.class);
        
        try {
            tx = session.beginTransaction();

            session.createQuery("DELETE FROM Employee"); // delete all records of Employee class in database
            employeeDB = (Employee) session.get(Employee.class, 1);

            employeeDB.setFirstName("Jan");
            employeeDB.setLastName("Kowalski");
            employeeDB.setSalary(999);
            employeeDB.setCertificates(new HashSet(Arrays.asList(new Certificate("XYZ"))));
            session.update(employeeDB);

            tx.rollback();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        }

        // database is still empty
        assertNull(employeeDB);
    }
}
