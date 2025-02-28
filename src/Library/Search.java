package Library;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// The Search class implements the IOOperation interface
public class Search implements IOOperation {

    // Overrides the oper method from the IOOperation interface
    @Override
    public void oper(Database database, User user) {
        
        // Create a new frame for searching a book
        JFrame frame = Main.frame(400, 210);
        frame.setLayout(new BorderLayout());
        
        // Create and add a title label to the frame
        JLabel title = Main.title("Search");
        frame.getContentPane().add(title, BorderLayout.NORTH);
        
        // Create a panel with a grid layout for input fields and buttons
        JPanel panel = new JPanel(new GridLayout(2, 2, 15, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
        panel.setBackground(null);
        
        // Create and add labels and text fields to the panel
        JLabel label = Main.label("Book Name:");
        JTextField name = Main.textfield();
        JButton search = Main.button("Search");
        JButton cancel = Main.button("Cancel");
        panel.add(label);
        panel.add(name);
        panel.add(search);
        panel.add(cancel);
        
        // Add action listener to the search button
        search.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
                if (name.getText().toString().matches("")) {
                    JOptionPane.showMessageDialog(new JFrame(), "Book name cannot be empty!");
                    return;
                }
                int i = database.getBook(name.getText().toString());
                if (i > -1) {
                    JOptionPane.showMessageDialog(new JFrame(), database.getBook(i).toString());
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

}
