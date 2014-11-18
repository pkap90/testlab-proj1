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

  private final Certificate certificate1;
  private final Certificate certificate2;
  private final String name;
  private final int id;
  private final int hash;
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
      {"yada", 13, false},
      {"1JLPT 4", 1, false}
    };
  }
  
  @Parameters
  public static Collection data() {
    return Arrays.asList(new Object[][] {
      {"FCE", 2, 2 + 13 * "FCE".hashCode(), cmpList(0)},
      {"CAE", 3, 3 + 13 * "CAE".hashCode(), cmpList(1)},
      {"CPE", 5, 5 + 13 * "CPE".hashCode(), cmpList(2)},
      {"MBA", 7, 7 + 13 * "MBA".hashCode(), cmpList(3)},
      {"JLPT 4", 11, 11 + 13 * "JLPT 4".hashCode()/*1393797010426*/, cmpList(4)}
    });
  }
  
  public CertificateTest(String name, int id, int hash, Object[][] cmpList) {
    cmp = cmpList;
    this.name = name;
    this.id = id;
    this.hash = hash;
    certificate1 = new Certificate(name);
    certificate2 = new Certificate();
    certificate1.setId(id);
    certificate2.setId(id);
    certificate2.setName(name);
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
    assertEquals(name, certificate1.getName());
    assertEquals(id, certificate1.getId());
  }
  
  @Test
  public void testAccessors() {
    assertEquals(name, certificate2.getName());
    assertEquals(id, certificate2.getId());
  }
  
  @Test
  public void testEquals() {
    Certificate certificate3;
    for (Object[] cmpRow : cmp) {
      certificate3 = new Certificate((String)cmpRow[0]);
      certificate3.setId((int)cmpRow[1]);
      assertTrue(certificate1.equals(certificate1));
      assertTrue(certificate2.equals(certificate2));
      assertTrue(certificate1.equals(certificate2));
      assertEquals(certificate1.equals(certificate3), (boolean)cmpRow[2]);
      assertEquals(certificate2.equals(certificate3), (boolean)cmpRow[2]);
    }
  }
  
  @Test
  public void testHashCode() {
    assertEquals(certificate1.hashCode(), hash);
    assertEquals(certificate2.hashCode(), hash);
    // Taki sam dla takich samych, inny dla roznych
    Certificate certificate3;
    for (Object[] cmpRow : cmp) {
      certificate3 = new Certificate((String)cmpRow[0]);
      certificate3.setId((int)cmpRow[1]);
      assertEquals(certificate1.equals(certificate3), certificate1.hashCode() == certificate3.hashCode());
    }
  }
}
