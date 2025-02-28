package Library;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// The User class is an abstract class representing a library user
public abstract class User {
    
    protected String name; // Name of the user
    protected String email; // Email of the user
    protected String phonenumber; // Phone number of the user
    protected IOOperation[] operations; // Array of user operations
    
    // Default constructor
    public User() {}
    
    // Constructor that takes the name parameter
    public User(String name) {
        this.name = name;
    }
    
    // Constructor that takes name, email, and phone number parameters
    public User(String name, String email, String phonenumber) {
        this.name = name;
        this.email = email;
        this.phonenumber = phonenumber;
    }

    // Getter method for the name field
    public String getName() {
        return name;
    }
    
    // Getter method for the email field
    public String getEmail() {
        return email;
    }
    
    // Getter method for the phone number field
    public String getPhoneNumber() {
        return phonenumber;
    }
    
    // Abstract method to provide a string representation of the user
    abstract public String toString();
    
    // Abstract method to display the user menu
    abstract public void menu(Database database, User user);
    
    // Method to create and return a JFrame for the user menu
    public JFrame frame(String[] data, Database database, User user) {
        JFrame frame = new JFrame();
        frame.setSize(400, 500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Library Management System");
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(null);
        frame.setBackground(null);
        
        // Create and add a welcome label to the frame
        JLabel label1 = Main.title("Welcome Mr. " + this.name);
        frame.getContentPane().add(label1, BorderLayout.NORTH);
        
        // Create a panel with a grid layout for the menu buttons
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(0, 30, 30, 30));
        panel.setLayout(new GridLayout(7, 1, 15, 15));
        panel.setBackground(null);
        
        // Create and add buttons for each menu option to the panel
        for (int i = 0; i < 7; i++) {
            JButton button = new JButton(data[i]);
            button.setFont(new Font("Tahoma", Font.BOLD, 17));
            button.setForeground(Color.white);
            button.setHorizontalAlignment(SwingConstants.CENTER);
            button.setBackground(Color.decode("#1da1f2"));
            button.setBorder(null);
            int index = i;
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {        
                    operations[index].oper(database, user);
                    if (data[index].matches("Exit") || data[index].matches("Delete all data")) {
                        frame.dispose();
                    }
                }
            });
            panel.add(button);
        }
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        return frame;
    }
}
