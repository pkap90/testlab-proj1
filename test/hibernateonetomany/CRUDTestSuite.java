package hibernateonetomany;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
   DbCreateOneTest.class,
   DbCreateOneNullTest.class,
   DbCreateOneWrongTest.class,
   DbCreateManyTest.class,
   DbReadOneTest.class
})
public class CRUDTestSuite {
}
