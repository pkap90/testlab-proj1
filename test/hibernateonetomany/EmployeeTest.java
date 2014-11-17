package hibernateonetomany;

import java.util.*;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.*;
import org.junit.runners.*;
import org.junit.runners.Parameterized.*;

@RunWith(Parameterized.class)
public class EmployeeTest {

  private final Employee em1;
  private final Employee em2;
  private final String fn;
  private final String ln;
  private final int s;
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
    fn = fname;
    ln = lname;
    s = sal;
    this.id = id;
    em1 = new Employee(fname, lname, sal);
    em1.setId(id);
    em2 = new Employee();
    em2.setFirstName(fname);
    em2.setLastName(lname);
    em2.setSalary(sal);
    em2.setId(id);
  }

  @Test
  public void testConstructor() {
    assertEquals(fn, em1.getFirstName());
    assertEquals(ln, em1.getLastName());
    assertEquals(s, em1.getSalary());
    assertEquals(id, em1.getId());
    assertEquals(fn, em2.getFirstName());
    assertEquals(ln, em2.getLastName());
    assertEquals(s, em2.getSalary());
    assertEquals(id, em2.getId());
  }
}
