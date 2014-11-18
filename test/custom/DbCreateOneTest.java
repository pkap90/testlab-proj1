package custom;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.Session;
import org.hibernate.Transaction;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/*
 Test Adding one Emplyee to database 
 */
@RunWith(Parameterized.class)
public class DbCreateOneTest {

    private final Shelf shelf;
    private final Set books;

    @Parameters
    public static Collection<Object[]> employees() {
        return Arrays.asList(new Object[][]{
            {"A1","Upper", new HashSet(Arrays.asList(new Book("Bajki", "Ignacy Krasicki")))},
            {"B10","Middle", new HashSet(Arrays.asList(new Book("Chlopi", "Wladyslaw Reymont")))},
            {"C99","Lower", new HashSet(Arrays.asList(new Book("Dziady", "Adam Mickiewicz")))},
            {null, null, new HashSet(Arrays.asList(new Book("Faraon", "Boleslaw Prus")))}
        });
    }

    public DbCreateOneTest(String name, String location, Set set) {
        shelf = new Shelf(name, location);           
        books = set;
        shelf.setBooks(books);
    }

    @Test
    public void test() {
        Session session = SessionConfiguration.getSessionFactory().openSession();
        Transaction tx = null;

        Shelf shelfDB = null;
        Integer id = null;

        try {
            tx = session.beginTransaction();

            session.createQuery("DELETE FROM Employee"); // delete all records of Employee class in database  
            
            id = (Integer) session.save(shelf);
            shelfDB = (Shelf) session.get(Shelf.class, id);

            tx.rollback();
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        }

        assertTrue(id != null);          // if employee is in database then ID is set
        assertTrue(shelfDB != null);  // check if employee really exists in database
    }
}
