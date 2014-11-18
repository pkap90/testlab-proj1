package hibernateonetomany;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    // Create
    DbCreateOneTest.class,
    DbCreateOneNullTest.class,
    DbCreateOneWrongTest.class,
    DbCreateManyTest.class,
    // Read
    DbReadOneTest.class,
    DbReadOneNullTest.class,
    DbReadEmptyDbTest.class,
    DbReadManyTest1.class,
    DbReadManyTest2.class,
    DbReadManyTest3.class,
    // Update
    DbUpdateTest.class,
    DbUpdateEmptyDbTest.class,
    // Delete
    DbDeleteOneTest.class,
    DbDeleteEmptyDbTest.class
})
public class CRUDTestSuite {
}
