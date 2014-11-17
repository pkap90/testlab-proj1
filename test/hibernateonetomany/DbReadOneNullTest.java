package hibernateonetomany;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;
import static org.junit.Assert.*;

/*
 Read value from database that does not exists
 */
public class DbReadOneNullTest extends BaseDbTest{

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

        // READ values - Check for equality
        assertNull(employeeDB.getLastName());
        assertNull(employeeDB.getFirstName());
        assertEquals(0, employeeDB.getSalary());
        assertNull(employeeDB.getCertificates());

        assertEquals(setId.intValue(), employeeDB.getId());
        assertEquals(employee, employeeDB);
        assertEquals(employee.getFirstName(), employeeDB.getFirstName());
        assertEquals(employee.getLastName(), employeeDB.getLastName());
        assertEquals(employee.getSalary(), employeeDB.getSalary());
        assertEquals(employee.getCertificates(), employeeDB.getCertificates());
    }
}
