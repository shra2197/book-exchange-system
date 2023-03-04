package model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class Book {
    private String title;
    private String author;
    private List<Review> bookReviews;
    private List<Rating> bookRatings;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.bookReviews = new ArrayList<>();
        this.bookRatings = new ArrayList<>();
    }
}
