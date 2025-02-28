package Library;

import javax.swing.JFrame;

// The NormalUser class inherits from the User class
public class NormalUser extends User {

    // Constructor that takes only the name parameter
    public NormalUser(String name) {
        super(name); // Calls the parent class (User) constructor
        this.operations = new IOOperation[] {
                new ViewBooks(),
                new Search(),
                new PlaceOrder(),
                new BorrowBook(),
                new CalculateFine(),
                new ReturnBook(),
                new Exit()
        };
    }
    
    // Constructor that takes name, email, and phone number parameters
    public NormalUser(String name, String email, String phonenumber) {
        super(name, email, phonenumber); // Calls the parent class (User) constructor
        this.operations = new IOOperation[] {
                new ViewBooks(),
                new Search(),
                new PlaceOrder(),
                new BorrowBook(),
                new CalculateFine(),
                new ReturnBook(),
                new Exit()
        };
    }
    
    // Overrides the menu method from the User class
    @Override
    public void menu(Database database, User user) {
        
        // Array of menu options
        String[] data = new String[7];
        data[0] = "View Books";
        data[1] = "Search";
        data[2] = "Place Order";
        data[3] = "Borrow Book";
        data[4] = "Calculate Fine";
        data[5] = "Return Book";
        data[6] = "Exit";
        
        // Create and display the menu frame
        JFrame frame = this.frame(data, database, user);
        frame.setVisible(true);
    }
    
    // Overrides the toString method to provide a custom string representation
    public String toString() {
        return name + "<N/>" + email + "<N/>" + phonenumber + "<N/>" + "Normal";
    }
}
