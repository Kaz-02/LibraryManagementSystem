package Library;

import java.awt.*;
import javax.swing.*;

// The ViewBooks class implements the IOOperation interface
public class ViewBooks implements IOOperation {

    // Overrides the oper method from the IOOperation interface
    @Override
    public void oper(Database database, User user) {
        
        // Calculate the number of rows for the grid layout
        int rows = database.getAllBooks().size() + 1;
        int height = rows * 60 + 100;
        
        // Create a new frame to view books
        JFrame frame = Main.frame(1000, height);
        
        // Create and add a title label to the frame
        JLabel title = Main.title("View Books");
        frame.getContentPane().add(title, BorderLayout.NORTH);
        
        // Create a panel with a grid layout for displaying books
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(rows, 7, 0, 0));
        panel.setBorder(BorderFactory.createEmptyBorder(0, 30, 30, 30));
        panel.setBackground(null);
        
        // Header row labels
        String[] row1 = new String[] {"Name", "Author", "Publisher", "CLA", "Qty", "Price", "Brw cps"};
        for (String s : row1) {
            JLabel label = label(s);
            panel.add(label);
        }
        
        // Populate the panel with book details
        for (Book b : database.getAllBooks()) {
            JLabel label1 = label(b.getName());
            JLabel label2 = label(b.getAuthor());
            JLabel label3 = label(b.getPublisher());
            JLabel label4 = label(b.getAdress());
            JLabel label5 = label(String.valueOf(b.getQty()));
            JLabel label6 = label(String.valueOf(b.getPrice()));
            JLabel label7 = label(String.valueOf(b.getBrwcopies()));
            panel.add(label1);
            panel.add(label2);
            panel.add(label3);
            panel.add(label4);
            panel.add(label5);
            panel.add(label6);
            panel.add(label7);
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
