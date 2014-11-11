package hibernateonetomany;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
import java.util.*;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.*;
import org.junit.runners.*;
import org.junit.runners.Parameterized.*;

/**
 *
 * @author Paweł
 */
@RunWith(Parameterized.class)
public class CertificateTest {

  private final Certificate c1;
  private final Certificate c2;
  private final String n;
  private final int id;
  private final int h;
  private final Object[][] cmp;
  
  public static Object[][] cmpList(int i) {
    return new Object [][] {
      {"FCE", 2, i == 0},
      {"FCE", 4, false},
      {"error", 2, false},
      {"CAE", 3, i == 1},
      {"CAE", 1, false},
      {"wth", 3, false},
      {"CPE", 5, i == 2},
      {"CPE", 7, false} ,
      {"unknown", 5, false},
      {"MBA", 0, false},
      {"MBA", 7, i == 3},
      {"asdf", 130, false},
      {"JLPT 4", 11, i == 4},
      {"JLPT 4", 17, false},
      {"yada", 13, false}
    };
  }
  
  @Parameters
  public static Collection data() {
    return Arrays.asList(new Object[][] {
      {"FCE", 2, 1558966, cmpList(0)},
      {"CAE", 3, 1585812, cmpList(1)},
      {"CPE", 5, 1645859, cmpList(2)},
      {"MBA", 7, 1714613, cmpList(3)},
      {"JLPT 4", 11, "11JLPT 4".hashCode()/*1393797010426*/, cmpList(4)}
    });
  }
  
  public CertificateTest(String name, int id, int hash, Object[][] cmpList) {
    cmp = cmpList;
    n = name;
    this.id = id;
    this.h = hash;
    c1 = new Certificate(name);
    c2 = new Certificate();
    c1.setId(id);
    c2.setId(id);
    c2.setName(name);
  }

  /*@BeforeClass
  public static void setUpClass() {
  }*/

  /*@AfterClass
  public static void tearDownClass() {
  }*/

  /*@Before
  public void setUp() {
  }*/

  /*@After
  public void tearDown() {
  }*/

    // TODO add test methods here.
  // The methods must be annotated with annotation @Test. For example:
  //
  // @Test
  // public void hello() {}

  @Test
  public void testConstructor() {
    assertEquals(n, c1.getName());
    assertEquals(id, c1.getId());
  }
  
  @Test
  public void testAccessors() {
    assertEquals(n, c2.getName());
    assertEquals(id, c2.getId());
  }
  
  @Test
  public void testEquals() {
    Certificate c3;
    for (Object[] cmpRow : cmp) {
      c3 = new Certificate((String)cmpRow[0]);
      c3.setId((int)cmpRow[1]);
      assertTrue(c1.equals(c1));
      assertTrue(c2.equals(c2));
      assertTrue(c1.equals(c2));
      assertEquals(c1.equals(c3), (boolean)cmpRow[2]);
      assertEquals(c2.equals(c3), (boolean)cmpRow[2]);
    }
  }
  
  @Test
  public void testHashCode() {
    assertEquals(c1.hashCode(), h);
    assertEquals(c2.hashCode(), h);
  }
}
