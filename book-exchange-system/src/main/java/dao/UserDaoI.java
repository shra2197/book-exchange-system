package dao;

import model.Book;
import model.User;

import java.util.List;

public interface UserDaoI {
    void saveUser(User user);
    User getUser(String userName);
    void updateUser(User user);
    List<Book> getAllAvailableExchangeBooks();
    List<User> getAllUsers();
}
