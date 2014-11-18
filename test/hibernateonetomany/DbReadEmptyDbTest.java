package hibernateonetomany;

import org.junit.Test;
import static org.junit.Assert.*;

/*
 Test Reading Employee from empty database 
 */
public class DbReadEmptyDbTest extends BaseDbTest{

    @Test
    public void test() {
        Employee employeeDB = null;

        employeeDB = (Employee) session.get(Employee.class, 1);

        assertNull(employeeDB); // database is empty
    }
}
