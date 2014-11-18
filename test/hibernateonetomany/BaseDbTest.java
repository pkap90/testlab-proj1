package hibernateonetomany;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;

public class BaseDbTest {
    
    Session session;
    Transaction tx;
    
    @Before
    public void setUp() {
        session = SessionConfiguration.getSessionFactory().openSession();
        try {
            tx = session.beginTransaction();

            session.createQuery("DELETE FROM Employee"); // delete all records of Employee class in database  
            session.createQuery("DELETE FROM Certificate"); // delete all records of Certificate class in database  
            
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        }
    }
    
    @After
    public void tearDown() {
        tx.rollback();
        session.close();
        tx = null;
        session = null; 
    }
    
}
