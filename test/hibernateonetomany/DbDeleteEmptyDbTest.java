package hibernateonetomany;

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/*
 Test Deleting Employee from empty database 
 */
public class DbDeleteEmptyDbTest extends BaseDbTest{

    @Test
    public void test() {
        List employeesDB = null;
  
        employeesDB = session.createQuery("FROM Employee").list();
            
        // database is still empty
        assertFalse(employeesDB.size() > 0);
        assertEquals(employeesDB.size(), 0);
    }
}
