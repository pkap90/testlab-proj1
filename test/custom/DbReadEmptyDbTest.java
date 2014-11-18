package hibernateonetomany;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;
import static org.junit.Assert.*;

/*
 Test Reading Employee from empty database 
 */
public class DbReadEmptyDbTest {

    @Test
    public void test() {
        Session session = SessionConfiguration.getSessionFactory().openSession();
        Transaction tx = null;

        Employee employeeDB = null;

        try {
            tx = session.beginTransaction();

            session.createQuery("DELETE FROM Employee"); // delete all records of Employee class in database
            employeeDB = (Employee) session.get(Employee.class, 1);

            tx.rollback();
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        }

        assertNull(employeeDB); // database is empty
    }
}
