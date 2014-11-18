package hibernateonetomany;

import java.util.*;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.*;
import org.junit.runners.*;
import org.junit.runners.Parameterized.*;

@RunWith(Parameterized.class)
public class EmployeeTest {

  private final Employee employee1;
  private final Employee employee2;
  private final String firstName;
  private final String lastName;
  private final int salary;
  private final int id;
  @Parameters
  public static Collection data() {
    return Arrays.asList(new Object[][] {
      {"Jan", "Nowak", 1500, 2},
      {"Qing-jao", "Han", 8000, 3},
      {"Tsugumi", "Oga", 2000, 5},
      {"Donald", "Duck", 700, 7},
      {"Marcin", "Wojciechowski", 4000, 11},
      {"Paweł", "Polański", 500, 13},
      {"Danuta", "Chmurska", 2500, 17}
    });
  }
  public EmployeeTest(String fname, String lname, int sal, int id) {
    firstName = fname;
    lastName = lname;
    salary = sal;
    this.id = id;
    employee1 = new Employee(fname, lname, sal);
    employee1.setId(id);
    employee2 = new Employee();
    employee2.setFirstName(fname);
    employee2.setLastName(lname);
    employee2.setSalary(sal);
    employee2.setId(id);
  }

  @Test
  public void testConstructor() {
    assertEquals(firstName, employee1.getFirstName());
    assertEquals(lastName, employee1.getLastName());
    assertEquals(salary, employee1.getSalary());
    assertEquals(id, employee1.getId());
    assertEquals(firstName, employee2.getFirstName());
    assertEquals(lastName, employee2.getLastName());
    assertEquals(salary, employee2.getSalary());
    assertEquals(id, employee2.getId());
  }
}
