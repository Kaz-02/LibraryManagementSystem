package Library;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;

public class Main {
    
    // Scanner for user input
    protected static Scanner s;
    // Database instance
    protected static Database database;

    public static void main(String[] args) {
        
        // Initialize the database
        database = new Database();
        
        // Create the main frame
        JFrame frame = frame(500, 300);
        
        // Create a panel with a grid layout
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 15, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 15, 20, 15));
        panel.setBackground(null);
        
        // Create and add a title label to the frame
        JLabel title = label("Welcome to Library Management System");
        title.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        title.setFont(new Font("Tahoma", Font.BOLD, 21));
        title.setForeground(Color.decode("#1da1f2"));
        frame.getContentPane().add(title, BorderLayout.NORTH);
        
        // Create labels and text fields for phone number and email
        JLabel label1 = label("Phone Number:");
        JLabel label2 = label("Email:");
        JTextField phonenumber = textfield();
        JTextField email = textfield();
        // Create buttons for login and new user
        JButton login = button("Login");
        JButton newUser = button("New User");
        
        // Add action listener to login button
        login.addActionListener(new ActionListener() {      
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validate phone number and email fields
                if (phonenumber.getText().toString().matches("")) {
                    JOptionPane.showMessageDialog(new JFrame(), "Phone number cannot be empty!");
                    return;
                }
                if (email.getText().toString().matches("")) {
                    JOptionPane.showMessageDialog(new JFrame(), "Email cannot be empty!");
                    return;
                }
                // Perform login
                login(phonenumber.getText().toString(), email.getText().toString(), frame);
            }   
        });
        // Add action listener to new user button
        newUser.addActionListener(new ActionListener() {        
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open new user creation window
                newuser();
                frame.dispose();
            }   
        });
        
        // Add components to the panel
        panel.add(label1);
        panel.add(phonenumber);
        panel.add(label2);
        panel.add(email);   
        panel.add(login);
        panel.add(newUser);
        
        // Add panel to the frame and make it visible
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    // Method to handle user login
    private static void login(String phonenumber, String email, JFrame frame) {
        int n = database.login(phonenumber, email);
        // Check if user exists in the database
        if (n != -1) {
            // Get user and display menu
            User user = database.getUser(n);
            user.menu(database, user);
            frame.dispose();
        } else {
            JOptionPane.showMessageDialog(new JFrame(), "User doesn't exist");
        }
    }
    
    // Method to create a new user account
    private static void newuser() {
        
        // Create a new frame for user registration
        JFrame frame = frame(500, 400);
        
        // Create a panel with a grid layout
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 15, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 15, 20, 15));
        panel.setBackground(null);
        
        // Create and add a title label to the frame
        JLabel title = label("Create new account");
        title.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        title.setFont(new Font("Tahoma", Font.BOLD, 21));
        title.setForeground(Color.decode("#1da1f2"));
        frame.getContentPane().add(title, BorderLayout.NORTH);
        
        // Create labels and text fields for name, phone number, and email
        JLabel label0 = label("Name:");
        JLabel label1 = label("Phone Number:");
        JLabel label2 = label("Email:");
        JTextField name = textfield();
        JTextField phonenumber = textfield();
        JTextField email = textfield();
        // Create radio buttons for account type
        JRadioButton admin = radioButton("Admin");
        JRadioButton normaluser = radioButton("Normal User");
        // Create buttons for creating account and canceling
        JButton createacc = button("Create Account");
        JButton cancel = button("Cancel");
        
        // Add action listeners to radio buttons to ensure only one is selected
        admin.addActionListener(e -> {
            if (normaluser.isSelected()) {
                normaluser.setSelected(false);
            }
        });
        normaluser.addActionListener(e -> {
            if (admin.isSelected()) {
                admin.setSelected(false);
            }
        });
        
        // Add components to the panel
        panel.add(label0);
        panel.add(name);
        panel.add(label1);
        panel.add(phonenumber);
        panel.add(label2);
        panel.add(email);
        panel.add(admin);
        panel.add(normaluser);
        panel.add(createacc);
        panel.add(cancel);
        
        // Add action listener to create account button
        createacc.addActionListener(new ActionListener() {      
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validate user input
                if (database.userExists(name.getText().toString())) {
                    JOptionPane.showMessageDialog(new JFrame(), "Username exists!\nTry another one");
                    return;
                }
                if (name.getText().toString().matches("")) {
                    JOptionPane.showMessageDialog(new JFrame(), "Name cannot be empty!");
                    return;
                }
                if (phonenumber.getText().toString().matches("")) {
                    JOptionPane.showMessageDialog(new JFrame(), "Phone number cannot be empty!");
                    return;
                }
                if (email.getText().toString().matches("")) {
                    JOptionPane.showMessageDialog(new JFrame(), "Email cannot be empty!");
                    return;
                }
                if (!admin.isSelected() && !normaluser.isSelected()) {
                    JOptionPane.showMessageDialog(new JFrame(), "You must choose account type!");
                    return;
                }
                // Create user account
                User user;
                if (admin.isSelected()) {
                    user = new Admin(name.getText().toString(),
                            email.getText().toString(), phonenumber.getText().toString());
                } else {
                    user = new NormalUser(name.getText().toString(),
                            email.getText().toString(), phonenumber.getText().toString());
                }
                frame.dispose();
                database.AddUser(user);
                user.menu(database, user);	
			}
		});
		cancel.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.setVisible(true);
	}
	
    // Function to create a JFrame
    public static JFrame frame(int width, int height) {
        JFrame frame = new JFrame();
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Library Management System");
        frame.setLayout(new BorderLayout());
        frame.setBackground(Color.white);
        frame.getContentPane().setBackground(Color.white);
        return frame;
    }
    
    // Function to create a JLabel with specific text
    public static JLabel label(String text) {
        JLabel label1 = new JLabel(text);
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setFont(new Font("Arial", Font.BOLD, 17));
        label1.setForeground(Color.black);
        return label1;
    }
    
    // Function to create a JTextField
    public static JTextField textfield() {
        JTextField textfield1 = new JTextField();
        textfield1.setFont(new Font("Arial", Font.BOLD, 17));
        textfield1.setForeground(Color.black);
        textfield1.setHorizontalAlignment(SwingConstants.CENTER);
        return textfield1;
    }
    
    // Function to create a JButton with specific text
    public static JButton button(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 17));
        button.setForeground(Color.white);
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setBackground(Color.decode("#1da1f2"));
        button.setBorder(null);
        return button;
    }
    
    // Function to create a JRadioButton with specific text
    public static JRadioButton radioButton(String text) {
        JRadioButton btn = new JRadioButton();
        btn.setForeground(Color.black);
        btn.setText(text);
        btn.setHorizontalAlignment(SwingConstants.CENTER);
        btn.setFont(new Font("Arial", Font.BOLD, 17));
        btn.setBackground(null);
        return btn;
    }
    
    // Function to create a JLabel for the title with specific text
    public static JLabel title(String text) {
        JLabel title = Main.label(text);
        title.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        title.setFont(new Font("Arial", Font.BOLD, 21));
        title.setForeground(Color.decode("#1da1f2"));
        return title;
    }
}