package model;

import accessmanagement.AccessManager;
import enums.Permission;
import enums.UserRole;
import exception.InsufficientRewardPoints;
import exception.InvalidAccessPermission;
import exception.InvalidExchangeException;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data

public class User {
    private String username;
    private UserRole userRole;
    private int rewardPoints;
    private List<Book> exchangeBookList;
    private List<Review> userReviews;
    private List<Rating> userRatings;

    public User(String username) {
        this.username = username;
        this.exchangeBookList = new ArrayList<>();
        this.userRole = UserRole.GUEST;
    }

    public void addExchangeBooksList(List<Book> myBookList) {
        if (userRole.equals(UserRole.VERIFIED))
            exchangeBookList.addAll(myBookList);
    }

    public void updateBooksListWithRewardPoints(List<Book> booksToGive) {
        if(booksToGive.size() > exchangeBookList.size())
            throw  new InvalidExchangeException("User does not have requested books");
        exchangeBookList.removeAll(booksToGive);
        setRewardPoints(this.rewardPoints + 1);
    }

    public boolean checkPermissionForExchange() {
        if (!AccessManager.hasPermission(getUserRole(), Permission.EXCHANGE_BOOKS)) {
            throw new InvalidAccessPermission("User doesn't have permission to perform exchange operation");
        }
        return true;
    }

    public void payRewardPoint(){
        setRewardPoints(this.rewardPoints - 1);
    }

    public void checkRewardPoints(List<Book> requestedBooks){
        if(this.rewardPoints < requestedBooks.size())
            throw new InsufficientRewardPoints("User has insufficient reward points");
    }
}
