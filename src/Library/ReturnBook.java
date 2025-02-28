package Library;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// The ReturnBook class implements the IOOperation interface
public class ReturnBook implements IOOperation {

    // Overrides the oper method from the IOOperation interface
    @Override
    public void oper(Database database, User user) {
        
        // Create a new frame for returning a book
        JFrame frame = Main.frame(400, 210);
        frame.setLayout(new BorderLayout());
        
        // Create and add a title label to the frame
        JLabel title = Main.title("Return book");
        frame.getContentPane().add(title, BorderLayout.NORTH);
        
        // Create a panel with a grid layout for input fields and buttons
        JPanel panel = new JPanel(new GridLayout(2, 2, 15, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
        panel.setBackground(null);
        
        // Create and add labels and text fields to the panel
        JLabel label = Main.label("Book Name:");
        JTextField name = Main.textfield();
        JButton returnbook = Main.button("Return Book");
        JButton cancel = Main.button("Cancel");
        panel.add(label);
        panel.add(name);
        panel.add(returnbook);
        panel.add(cancel);
        
        // Add action listener to the return book button
        returnbook.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                if (name.getText().toString().matches("")) {
                    JOptionPane.showMessageDialog(new JFrame(), "Book name cannot be empty!");
                    return;
                }
                if (!database.getBrws().isEmpty()) {
                    for (Borrowing b : database.getBrws()) {
                        if (b.getBook().getName().matches(name.getText().toString()) && 
                                b.getUser().getName().matches(user.getName())) {
                            Book book = b.getBook();
                            int i = database.getAllBooks().indexOf(book);
                            if (b.getDaysLeft() > 0) {
                                JOptionPane.showMessageDialog(new JFrame(), "You are late!\n"
                                        + "You have to pay " + Math.abs(b.getDaysLeft() * 50) + " as fine");
                            }
                            book.setBrwcopies(book.getBrwcopies() + 1);
                            database.returnBook(b, book, i);
                            JOptionPane.showMessageDialog(new JFrame(), "Book returned\nThank you!");
                            frame.dispose();
                            break;
                        } else {
                            JOptionPane.showMessageDialog(new JFrame(), "You didn't borrow this book!");
                        }
                    }
                } else {
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
