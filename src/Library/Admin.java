package Library;

import javax.swing.JFrame;

// The Admin class inherits from the User class
public class Admin extends User {
    
    // Constructor that takes only the name parameter
    public Admin(String name) {
        super(name); // Calls the parent class (User) constructor
        this.operations = new IOOperation[] {
                new ViewBooks(),
                new AddBook(),
                new DeleteBook(),
                new Search(),
                new DeleteAllData(),
                new ViewOrders(),
                new Exit()
        };
    }
    
    // Constructor that takes name, email, and phone number parameters
    public Admin(String name, String email, String phonenumber) {
        super(name, email, phonenumber); // Calls the parent class (User) constructor
        this.operations = new IOOperation[] {
                new ViewBooks(),
                new AddBook(),
                new DeleteBook(),
                new Search(),
                new DeleteAllData(),
                new ViewOrders(),
                new Exit()
        };
    }
    
    // Overrides the menu method from the User class
    @Override
    public void menu(Database database, User user) {
        String[] data = new String[7];
        data[0] = "View Books";
        data[1] = "Add Book";
        data[2] = "Delete Book";
        data[3] = "Search";
        data[4] = "Delete all data";
        data[5] = "View Orders";
        data[6] = "Exit";
        
        // Create and display the menu frame
        JFrame frame = this.frame(data, database, user);
        frame.setVisible(true);
    }
    
    // Overrides the toString method to provide a custom string representation
    public String toString() {
        return name + "<N/>" + email + "<N/>" + phonenumber + "<N/>" + "Admin";
    }
}
