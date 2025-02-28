package Library;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// The CalculateFine class implements the IOOperation interface
public class CalculateFine implements IOOperation {

    // Overrides the oper method from the IOOperation interface
    @Override
    public void oper(Database database, User user) {
        
        // Create a new frame for calculating fine
        JFrame frame = Main.frame(400, 210);
        frame.setLayout(new BorderLayout());
        
        // Create and add a title label to the frame
        JLabel title = Main.title("Calculate Fine");
        frame.getContentPane().add(title, BorderLayout.NORTH);
        
        // Create a panel with a grid layout for input fields and buttons
        JPanel panel = new JPanel(new GridLayout(2, 2, 15, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
        panel.setBackground(null);
        
        // Create and add labels and text fields to the panel
        JLabel label = Main.label("Book Name:");
        JTextField name = Main.textfield();
        JButton calc = Main.button("Calculate Fine");
        JButton cancel = Main.button("Cancel");
        panel.add(label);
        panel.add(name);
        panel.add(calc);
        panel.add(cancel);
        
        // Add action listener to the calculate fine button
        calc.addActionListener(new ActionListener() {   
            @Override
            public void actionPerformed(ActionEvent e) {
                if (name.getText().toString().matches("")) {
                    JOptionPane.showMessageDialog(new JFrame(), "Book name cannot be empty!");
                    return;
                }       
                boolean g = true;   
                for (Borrowing b : database.getBrws()) {
                    if (b.getBook().getName().matches(name.getText().toString()) &&
                            b.getUser().getName().matches(user.getName())) {
                        if (b.getDaysLeft() > 0) {
                            JOptionPane.showMessageDialog(new JFrame(),
                                    "You are late!\n" + "You have to pay " + b.getDaysLeft() * 50 + " as fine");
                            frame.dispose();
                        } else {
                            JOptionPane.showMessageDialog(new JFrame(),
                                    "You don't have to pay fine");
                            frame.dispose();
                        }
                        g = false; 
                        break;
                    }
                }
                
                if (g) {
                    JOptionPane.showMessageDialog(new JFrame(), "You didn't borrow this book!");
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
