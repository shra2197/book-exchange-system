import static org.junit.Assert.*;

import exception.InsufficientRewardPoints;
import exception.InvalidExchangeException;
import exception.InvalidUserException;
import model.Book;
import model.User;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BookExchangeServiceTest {

    private BookExchangeService bookExchangeService;
    User user1;
    User user2;
    User user3;
    List<Book> bookList1;
    List<Book> bookList2;
    List<Book> bookList3;

    @Before
    public void setup() {
        bookExchangeService = new BookExchangeService();
        user1 = new User("Shraddha");
        user2 = new User("John");
        user3 = new User("Jeni");

        bookList1 = new ArrayList<>();
        bookList1.add(new Book("MyBook", "Dany M"));
        bookList1.add(new Book("MySecBook", "Rechard S"));
        bookList1.add(new Book("MyThirdBook", "Rechard S"));

        bookList2 = new ArrayList<>();
        bookList2.add(new Book("Work", "John P"));
        bookList2.add(new Book("Attitude is everything", "Jeff Keller"));

        bookList3 = new ArrayList<>();
        bookList3.add(new Book("Dogalapa", "Ashneer"));
    }

    @Test
    public void testRegisterUser() {
        bookExchangeService.registerUser(user1);
        bookExchangeService.registerUser(user2);
        bookExchangeService.registerUser(user3);
        assertEquals(3, bookExchangeService.getAllUsers().size());
    }

    @Test
    public void testBrowseBooksWhenNoBooksAdded() {
        List<Book> bookList = bookExchangeService.browseBooks("Guest");
        assertEquals(0, bookList.size());
    }

    @Test
    public void testBrowseBooksWhenBooksForVerified() {
        bookExchangeService.registerUser(user1);
        bookExchangeService.registerUser(user2);
        user1.addExchangeBooksList(bookList1);
        user2.addExchangeBooksList(bookList2);

        List<Book> bookList = bookExchangeService.browseBooks("Shraddha");
        assertEquals(5, bookList.size());
    }

    @Test
    public void testBrowseBooksForGuest() {
        bookExchangeService.registerUser(user1);//shraddha
        user1.addExchangeBooksList(bookList1);
        List<Book> bookList = bookExchangeService.browseBooks("Guest");
        assertEquals(3, bookList.size());
    }

    @Test
    public void testExchangeBooksForVerfied() {
        bookExchangeService.registerUser(user1);//Shraddha
        bookExchangeService.registerUser(user2);//John
        bookExchangeService.addExchangeBooks("Shraddha", bookList1);//3
        bookExchangeService.addExchangeBooks("John", bookList2);//2
        String res = bookExchangeService.exchangeBooks("Shraddha", Arrays.asList(bookList2.get(0))
                , "John", Arrays.asList(bookList1.get(0)));
        assertEquals("Exchanged successfully", res);
        assertEquals(2, bookExchangeService.getUser("Shraddha").getExchangeBookList().size());
        assertEquals(1, bookExchangeService.getUser("John").getExchangeBookList().size());


    }


    @Test(expected = InvalidUserException.class)
    public void testExchangeBooksForGuest() {
        bookExchangeService.registerUser(user1);
        user1.addExchangeBooksList(bookList1);
        String res = bookExchangeService.exchangeBooks("Shraddha", Arrays.asList(bookList3.get(0)),
                "Jeni", Arrays.asList(bookList1.get(0)));
    }

    @Test(expected = InvalidExchangeException.class)
    public void testInvalidExchangeException() {
        bookExchangeService.registerUser(user1);
        bookExchangeService.registerUser(user2);
        bookExchangeService.addExchangeBooks("Shraddha", bookList1);
        bookExchangeService.addExchangeBooks("John", bookList2);
        String res = bookExchangeService.exchangeBooks("Shraddha", Arrays.asList(bookList2.get(0),
                        bookList2.get(1), new Book("Test", "Test"))
                , "John", Arrays.asList(bookList1.get(0)));
    }

    @Test
    public void testBorrowBooks() {
        bookExchangeService.registerUser(user1);
        bookExchangeService.registerUser(user2);
        bookExchangeService.registerUser(user3);
        bookExchangeService.addExchangeBooks("Shraddha", bookList1);
        bookExchangeService.addExchangeBooks("John", bookList2);
        bookExchangeService.addExchangeBooks("Jeni", bookList3);
        bookExchangeService.exchangeBooks("Shraddha", Arrays.asList(bookList2.get(0)), "John", Arrays.asList(bookList1.get(0)));
        assertEquals(1, bookExchangeService.getUser("Shraddha").getRewardPoints());
        bookExchangeService.borrowBook("Shraddha", Arrays.asList(new Book("Dogalapa", "Ashneer")));
        assertEquals(0, bookExchangeService.getUser("Shraddha").getRewardPoints());
        assertEquals(1, bookExchangeService.getUser("Jeni").getRewardPoints());
    }

    @Test(expected = InsufficientRewardPoints.class)
    public void testInsufficientRewardPointsException() {
        bookExchangeService.registerUser(user1);
        bookExchangeService.registerUser(user2);
        bookExchangeService.addExchangeBooks("John", bookList2);
        bookExchangeService.borrowBook("Shraddha", Arrays.asList(new Book("Attitude is everything", "Jeff Keller")));
    }
}
