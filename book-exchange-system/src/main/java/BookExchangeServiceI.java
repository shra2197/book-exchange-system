import model.Book;
import model.User;

import java.util.List;

public interface BookExchangeServiceI {
    List<Book> browseBooks(String username);
    void registerUser(User user);

    String exchangeBooks(String user1, List<Book> user1ExchangeList, String user2, List<Book> user2ExchangeList);
    public void borrowBook(String username, List<Book> requestedBooks);
}
