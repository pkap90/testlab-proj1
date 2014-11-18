package hibernateonetomany;

import org.junit.Test;
import static org.junit.Assert.*;

/*
 Test Adding Employee with null values to database
 */
public class DbCreateOneNullTest extends BaseDbTest{

    @Test
    public void test() {

        Employee employee = new Employee(null, null, 0);
        employee.setCertificates(null);

        Employee employeeDB = null;
        Integer setId = null;
            
        setId = (Integer) session.save(employee);
        employeeDB = (Employee) session.get(Employee.class, setId);

        assertTrue(setId != null);          // if employee is in database then ID is set
        assertTrue(employeeDB != null);     // check if employee really exists in database
    }
}
