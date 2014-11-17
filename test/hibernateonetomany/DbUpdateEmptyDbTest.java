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
public class DbUpdateEmptyDbTest extends BaseDbTest{

    @Rule
    public ExpectedException exception = ExpectedException.none();
    
    @Test
    public void test() {
        Employee employeeDB = null;
        
        exception.expect(NullPointerException.class);
        
        employeeDB = (Employee) session.get(Employee.class, 1);

        employeeDB.setFirstName("Jan");
        employeeDB.setLastName("Kowalski");
        employeeDB.setSalary(999);
        employeeDB.setCertificates(new HashSet(Arrays.asList(new Certificate("XYZ"))));
        session.update(employeeDB);

        // database is still empty
        assertNull(employeeDB);
    }
}
