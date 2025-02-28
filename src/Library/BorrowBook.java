package Library;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// The BorrowBook class implements the IOOperation interface
public class BorrowBook implements IOOperation {

    // Overrides the oper method from the IOOperation interface
    @Override
    public void oper(Database database, User user) {
        
        // Create a new frame for borrowing a book
        JFrame frame = Main.frame(400, 210);
        frame.setLayout(new BorderLayout());
        
        // Create and add a title label to the frame
        JLabel title = Main.title("Borrow book");
        frame.getContentPane().add(title, BorderLayout.NORTH);
        
        // Create a panel with a grid layout for input fields and buttons
        JPanel panel = new JPanel(new GridLayout(2, 2, 15, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
        panel.setBackground(null);
        
        // Create and add labels and text fields to the panel
        JLabel label = Main.label("Book Name:");
        JTextField name = Main.textfield();
        JButton borrow = Main.button("Borrow Book");
        JButton cancel = Main.button("Cancel");
        panel.add(label);
        panel.add(name);
        panel.add(borrow);
        panel.add(cancel);
        
        // Add action listener to the borrow book button
        borrow.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                if (name.getText().toString().matches("")) {
                    JOptionPane.showMessageDialog(new JFrame(), "Book name cannot be empty!");
                    return;
                }
                int i = database.getBook(name.getText().toString());
                if (i > -1) {
                    Book book = database.getBook(i);
                    boolean n = true;
                    for (Borrowing b : database.getBrws()) {
                        if (b.getBook().getName().matches(name.getText().toString()) && 
                                b.getUser().getName().matches(user.getName())) {
                            n = false;
                            JOptionPane.showMessageDialog(new JFrame(),
                                    "You have borrowed this book before!");
                        }
                    }
                    if (n) {
                        if (book.getBrwcopies() > 1) {
                            Borrowing borrowing = new Borrowing(book, user);
                            book.setBrwcopies(book.getBrwcopies() - 1);
                            database.borrowBook(borrowing, book, i);
                            JOptionPane.showMessageDialog(new JFrame(),
                                    "You must return the book before 14 days from now\n"
                                            + "Expiry date: " + borrowing.getFinish() + "\nEnjoy!\n");
                            frame.dispose();
                        } else {
                            JOptionPane.showMessageDialog(new JFrame(),
                                    "This book isn't available for borrowing");
                        }
                    }
                    
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
