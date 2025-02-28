package Library;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// The ViewOrders class implements the IOOperation interface
public class ViewOrders implements IOOperation {

    // Overrides the oper method from the IOOperation interface
    @Override
    public void oper(Database database, User user) {
        
        // Create a new frame for viewing orders
        JFrame frame = Main.frame(400, 210);
        frame.setLayout(new BorderLayout());
        
        // Create and add a title label to the frame
        JLabel title = Main.title("View Orders");
        frame.getContentPane().add(title, BorderLayout.NORTH);
        
        // Create a panel with a grid layout for input fields and buttons
        JPanel panel = new JPanel(new GridLayout(2, 2, 15, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
        panel.setBackground(null);
        
        // Create and add labels and text fields to the panel
        JLabel label = Main.label("Book Name:");
        JTextField name = Main.textfield();
        JButton view = Main.button("View Orders");
        JButton cancel = Main.button("Cancel");
        panel.add(label);
        panel.add(name);
        panel.add(view);
        panel.add(cancel);
        
        // Add action listener to the view orders button
        view.addActionListener(new ActionListener() {   
            @Override
            public void actionPerformed(ActionEvent e) {
                if (name.getText().toString().matches("")) {
                    JOptionPane.showMessageDialog(new JFrame(), "Book name cannot be empty!");
                    return;
                }
                int i = database.getBook(name.getText().toString());
                if (i > -1) {
                    viewOrders(name.getText().toString(), database);
                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "Book doesn't exist!");
                }
            }
        });

        // Add action listener to the cancel button
        cancel.addActionListener(new ActionListener() {         
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        
        // Add the panel to the center of the frame
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
    
    // Method to display the orders for a specific book
    private void viewOrders(String bookname, Database database) {
        
        // Calculate the number of rows needed for the grid layout
        int rows = 1;
        for (Order order : database.getAllOrders()) {
            if (order.getBook().getName().matches(bookname)) {
                rows++;
            }
        }
        int height = rows * 55 + 100;
        
        // Create a new frame to display the orders
        JFrame frame = Main.frame(500, height);
        
        // Create and add a title label to the frame
        JLabel title = Main.title("View Orders");
        frame.getContentPane().add(title, BorderLayout.NORTH);
        
        // Create a panel with a grid layout for displaying order details
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(rows, 4, 0, 0));
        panel.setBorder(BorderFactory.createEmptyBorder(0, 30, 30, 30));
        panel.setBackground(null);
        
        // Header row labels
        String[] row1 = new String[] {"Book", "User", "Qty", "Price"};
        for (String s : row1) {
            JLabel label = label(s);
            panel.add(label);
        }
        
        // Populate the panel with order details
        for (Order order : database.getAllOrders()) {
            if (order.getBook().getName().matches(bookname)) {
                JLabel label1 = label(order.getBook().getName());
                JLabel label2 = label(order.getUser().getName());
                JLabel label3 = label(String.valueOf(order.getQty()));
                JLabel label4 = label(String.valueOf(order.getPrice()));
                panel.add(label1);
                panel.add(label2);
                panel.add(label3);
                panel.add(label4);
            }
        }
        
        // Add the panel to the center of the frame
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.setVisible(true); 
    }
    
    // Helper method to create a JLabel with specific text and style
    private JLabel label(String text) {
        JLabel label = Main.label(text);
        label.setBackground(Color.white);
        label.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        label.setOpaque(true);
        return label;
    }
}
