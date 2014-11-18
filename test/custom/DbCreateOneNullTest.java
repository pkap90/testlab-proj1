package hibernateonetomany;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;
import static org.junit.Assert.*;

/*
 Test Adding Employee with null values to database
 */
public class DbCreateOneNullTest {

    @Test
    public void test() {
        Session session = SessionConfiguration.getSessionFactory().openSession();
        Transaction tx = null;

        Employee employee = new Employee(null, null, 0);
        employee.setCertificates(null);

        Employee employeeDB = null;
        Integer setId = null;

        try {
            tx = session.beginTransaction();

            session.createQuery("DELETE FROM Employee"); // delete all records of Employee class in database  
            
            setId = (Integer) session.save(employee);
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
    }
}
