package hibernateonetomany;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.hibernate.SessionFactory;

/**
 *
 * @author Mateusz
 */
public class ManageEmployeeTest {

    private SessionFactory factory;
    private Session session;
    private ManageEmployee ME;
    private Query query;
    
    private List<Employee> employees = new ArrayList<Employee>();
    private HashSet set;

    @Before
    public void setUp() {
        factory = createMock(SessionFactory.class);
        session = createMock(Session.class);
        query = createMock(Query.class);

        ME = new ManageEmployee();

        set = new HashSet();
        set.add(new Certificate("AAA"));
        set.add(new Certificate("BBB"));
        set.add(new Certificate("CCC"));

        employees.add(new Employee("Jan", "Kowalski", 5000));
        employees.add(new Employee("Alan", "Nowak", 4000));
        employees.add(new Employee("Karol", "Lee", 1000));
        employees.add(new Employee("Wojciech", "van Beethoven", 500));

    }

    @After
    public void tearDown() {
    }

    @Test
    public void addEmployeeTest() {
        Employee employee = new Employee("Jan", "Kowalski", 2000);

        //Session session = ME.factory.openSession();
       // int id = ME.addEmployee("Jan", "Kowalski", 2000, set);
       // Employee emp = (Employee) session.get(Employee.class, 1);
        //assertEquals(id, emp.getId());
        List<Employee> kumars = new ArrayList<Employee>();
        kumars.add(new Employee("Major", "Kumar", 4000));
        kumars.add(new Employee("Major", "Kumar", 4000));
        
        String hql = "from Employee APP where APP.LAST_NAME = :lastname";
        
        expect(factory.getCurrentSession()).andReturn(session);
        expect(session.createQuery(hql)).andReturn(query);
        
        expect(query.setParameter("lastname","Kumar")).andReturn(query);
        expect(query.list()).andReturn(kumars);
        replay(factory, session, query);
        
       // ME.setSessionFactory(factory);
        
        //assertEquals(theSmiths, dao.findByLastname(name));
        //verify(factory, session, query);

    }

    @Test
    public void listEmployeesTest() {

    }

    @Test
    public void updateEmployeeTest() {

    }

    @Test
    public void deleteEmployeeTest() {

    }

}
