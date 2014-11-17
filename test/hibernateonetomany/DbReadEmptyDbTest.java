package hibernateonetomany;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;
import static org.junit.Assert.*;

/*
    TODO

 Test Reading Employee from empty database 
 */
public class DbReadEmptyDbTest {

    @Test
    public void test() {
        Session session = SessionConfiguration.getSessionFactory().openSession();
        Transaction tx = null;

       List 
        try {
            tx = session.beginTransaction();

            employeeDB = (Employee) session.get(Employee.class, setId);

            tx.rollback();
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        }

        assertTrue(setId != null);          // if employee is in database then ID is set
        assertTrue(employeeDB != null);     // check if employee really exists in database

        // READ values - Check for equality
        assertNull(employeeDB.getLastName());
        assertNull(employeeDB.getFirstName());
        assertEquals(0, employeeDB.getSalary());
        assertNull(employeeDB.getCertificates());

        assertEquals(setId.intValue(), employeeDB.getId());
    }
}
