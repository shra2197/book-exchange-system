import accessmanagement.AccessManager;
import dao.UserDao;
import dao.UserDaoI;
import enums.Permission;
import enums.UserRole;
import model.Book;
import model.User;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class BookExchangeService implements BookExchangeServiceI {
    private UserDaoI userDao;

    public BookExchangeService() {
        userDao = new UserDao();
    }

    @Override
    public List<Book> browseBooks(String username) {
        User user = getBrowseUser(username);
        if (AccessManager.hasPermission(user.getUserRole(), Permission.BROWSE_BOOKS)) {
            return userDao.getAllAvailableExchangeBooks();
        }
        return null;
    }

    private User getBrowseUser(String username){
        try {
           return getUser(username);
        }catch (Exception e){
           return new User(username);
        }
    }

    @Override
    public void registerUser(User user) {
        user.setUserRole(UserRole.VERIFIED);
        userDao.saveUser(user);
    }

    @Override
    public String exchangeBooks(String username1, List<Book> user1ExpectedBooks, String username2, List<Book> user2ExpectedBooks) {
        User user1 = getUser(username1);
        User user2 = getUser(username2);
        user1.checkPermissionForExchange();
        user2.checkPermissionForExchange();
        user1.updateBooksListWithRewardPoints(user2ExpectedBooks);
        user2.updateBooksListWithRewardPoints(user1ExpectedBooks);
        userDao.updateUser(user1);
        userDao.updateUser(user2);
        return "Exchanged successfully";
    }

    public User getUser(String username){
        return userDao.getUser(username);
    }

    public List<User> getAllUsers(){
        return userDao.getAllUsers();
    }

    public void addExchangeBooks(String username, List<Book> booksAvailableForExchange){
        User user = getUser(username);
        user.addExchangeBooksList(booksAvailableForExchange);
    }

    public void borrowBook(String username, List<Book> requestedBooks){
        User user = getUser(username);
        user.checkRewardPoints(requestedBooks);
        requestedBooks.forEach(book->{
            User owner = getAllUsers().stream()
                    .filter(u -> !u.getUsername().equals(username) && u.getExchangeBookList().contains(book))
                    .findFirst().get();
            if(!Objects.isNull(owner)) {
                user.payRewardPoint();
                owner.updateBooksListWithRewardPoints(Arrays.asList(book));
            }
        });
    }


}
