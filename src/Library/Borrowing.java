package Library;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

// The Borrowing class represents a book borrowing operation
public class Borrowing {
    
    LocalDate start; // Start date of borrowing
    LocalDate finish; // Finish date of borrowing
    int daysleft; // Days left until the borrowing period ends
    Book book; // Book being borrowed
    User user; // User who borrowed the book
    
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Formatter for date
    
    // Constructor that initializes borrowing with the current date and a default period of 14 days
    public Borrowing(Book book, User user) {
        start = LocalDate.now(); // Set start date to today
        finish = start.plusDays(14); // Set finish date to 14 days from start
        daysleft = Period.between(start, finish).getDays(); // Calculate days left
        this.book = book;
        this.user = user;
    }
    
    // Constructor that initializes borrowing with specific start and finish dates
    public Borrowing(LocalDate start, LocalDate finish, Book book, User user) {
        this.start = start;
        this.finish = finish;
        this.daysleft = Period.between(finish, LocalDate.now()).getDays(); // Calculate days left
        this.book = book;
        this.user = user;
    }
    
    // Method to get the formatted start date
    public String getStart() {
        return formatter.format(start);
    }
    
    // Method to get the formatted finish date
    public String getFinish() {
        return formatter.format(finish);
    }
    
    // Method to get the number of days left until the finish date
    public int getDaysLeft() {
        return Period.between(finish, LocalDate.now()).getDays();
    }
    
    // Getter method for the book
    public Book getBook() {
        return book;
    }
    
    // Setter method for the book
    public void setBook(Book book) {
        this.book = book;
    }
    
    // Getter method for the user
    public User getUser() {
        return user;
    }
    
    // Setter method for the user
    public void setUser(User user) {
        this.user = user;
    }
    
    // Returns a string representation of the borrowing details
    public String toString() {
        return "Borrowing time: " + start + "\nExpiry date: " + finish + "\nDays left: " + daysleft;
    }
    
    // Returns a different string representation of the borrowing details
    public String toString2() {
        return getStart() + "<N/>" + getFinish() + "<N/>" + getDaysLeft() + "<N/>" + book.getName() + "<N/>" +
                user.getName() + "<N/>";
    }
}
