package dao;

import exception.InvalidUserException;
import model.Book;
import model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserDao implements UserDaoI{
    private Map<String, User> userMap;

    public UserDao(){
        userMap = new HashMap<>();
    }
    public void saveUser(User user){
        userMap.put(user.getUsername(),user);
    }

    public User getUser(String userName) throws InvalidUserException{
        if(!userMap.containsKey(userName))
            throw new InvalidUserException("User does not exist");
        return userMap.get(userName);
    }

    public void updateUser(User user){
        userMap.put(user.getUsername(), user);
    }

    public List<Book> getAllAvailableExchangeBooks(){
        return userMap.entrySet().stream().map(x -> x.getValue().getExchangeBookList()).flatMap(List::stream).collect(Collectors.toList());
    }

    public List<User> getAllUsers(){
        return userMap.values().stream().collect(Collectors.toList());
    }
}
