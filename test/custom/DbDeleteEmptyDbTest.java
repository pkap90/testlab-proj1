package hibernateonetomany;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;
import static org.junit.Assert.*;

/*
 Test Deleting Employee from empty database 
 */
public class DbDeleteEmptyDbTest {

    @Test
    public void test() {
        Session session = SessionConfiguration.getSessionFactory().openSession();
        Transaction tx = null;

        List employeesDB = null;

        try {
            tx = session.beginTransaction();

            session.createQuery("DELETE FROM Employee"); // delete all records of Employee class in database         
            employeesDB = session.createQuery("FROM Employee").list();
            
            tx.rollback();
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        }
        // database is still empty
        assertFalse(employeesDB.size() > 0);
        assertEquals(employeesDB.size(), 0);
    }
}
