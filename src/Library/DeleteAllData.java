package Library;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// The DeleteAllData class implements the IOOperation interface
public class DeleteAllData implements IOOperation {

    // Overrides the oper method from the IOOperation interface
    @Override
    public void oper(Database database, User user) {
        
        // Create a new frame for the delete all data operation
        JFrame frame = Main.frame(600, 150);
        frame.setLayout(new BorderLayout());
        
        // Create and add a title label to the frame
        JLabel title = Main.title("Are you sure that you want to delete all data?");
        frame.getContentPane().add(title, BorderLayout.NORTH);
        
        // Create a panel with a grid layout for the confirmation buttons
        JPanel panel = new JPanel(new GridLayout(1, 2, 15, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(0, 30, 20, 30));
        panel.setBackground(null);
        
        // Create and add buttons to the panel
        JButton del = Main.button("Continue");
        JButton cancel = Main.button("Cancel");
        panel.add(del);
        panel.add(cancel);
        
        // Add action listener to the continue button
        del.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                database.deleteAllData(); // Delete all data from the database
                frame.dispose();
                new Exit().oper(database, user); // Call the exit operation
            }
        });
        
        // Add action listener to the cancel button
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                user.menu(database, user); // Display the user menu
            }
        });
        
        // Add the panel to the center of the frame
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
