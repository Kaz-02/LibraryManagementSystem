package Library;

// The Order class represents an order for a book by a user
public class Order {
    
    private Book book; // Book associated with the order
    private User user; // User who placed the order
    private double price; // Price of the book
    private int qty; // Quantity of the book ordered
    
    // Constructor that initializes all fields of the Order class
    public Order(Book book, User user, double price, int qty) {
        this.book = book;
        this.user = user;
        this.price = price;
        this.qty = qty;
    }

    // Default constructor
    public Order() {}

    // Getter method for the book field
    public Book getBook() {
        return book;
    }

    // Setter method for the book field
    public void setBook(Book book) {
        this.book = book;
    }

    // Getter method for the user field
    public User getUser() {
        return user;
    }

    // Setter method for the user field
    public void setUser(User user) {
        this.user = user;
    }

    // Getter method for the price field
    public double getPrice() {
        return price;
    }

    // Setter method for the price field
    public void setPrice(double price) {
        this.price = price;
    }

    // Getter method for the qty field
    public int getQty() {
        return qty;
    }

    // Setter method for the qty field
    public void setQty(int qty) {
        this.qty = qty;
    }
    
    // Returns a string representation of the Order object
    public String toString() {
        return "Book name: " + book.getName() + "\n" +
                "Username: " + user.getName() + "\n" +
                "Price: " + String.valueOf(price) + "\n" +
                "Qty: " + String.valueOf(qty);
    }
    
    // Returns a different string representation of the Order object
    public String toString2() {
        return book.getName() + "<N/>" + user.getName() + "<N/>" + String.valueOf(price) + "<N/>" +
                String.valueOf(qty);
    }
}
