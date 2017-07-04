/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bottom_up_integraion_test;

import Boundary.BookManagment;
import Boundary.LibrarianHomePage;
import Boundary.Login;
import Controller.JDBC;
import Entity.Book;
import Entity.Librarian;
import database_unit_test.Configuration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JViewport;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import unit_test.Login_Test;

/**
 *
 * @author Biruk Adera
 */
public class Book_Add_Test {
    Librarian librarian;
    Book book;
    JDBC connection;
    
    @Before
    public void initialize() {
        Librarian newLibrarian = Configuration.librarian;
        newLibrarian.registerLibrarian();
        
        book = Configuration.book;
        
        connection = new JDBC();
    }
    
    @Test
    public void phase1_addBook() {
        librarian = connection.returnLibrarian(Configuration.librarianID);
        assertNotNull(librarian);
        assertTrue(librarian instanceof Librarian);
        
        Login login = new Login();
        login.setVisible(true);
        
        JTextField usernameField = (JTextField) login.findComponentAt(293, 426);
        JPasswordField passwordField = (JPasswordField) login.findComponentAt(293, 476);
        JButton loginButton = (JButton) login.findComponentAt(503, 526);
        
        usernameField.setText(librarian.getName());
        passwordField.setText(Configuration.librarian.getPassword());
        
        assertEquals(usernameField.getText(), librarian.getName());
        assertEquals(passwordField.getText(), Configuration.librarian.getPassword());
        
        delay();
        
        loginButton.doClick();
        
        LibrarianHomePage librarianHomePage = login.getLibrarianHomePage();
        Assert.assertTrue(librarianHomePage.isVisible());
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Login_Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        JButton bookManagmentButton = (JButton) librarianHomePage.findComponentAt(242, 339);
        bookManagmentButton.doClick();
        
        BookManagment bookManagment = librarianHomePage.getBookManagment();
        assertTrue(bookManagment.isVisible());
        
        JPanel outperPanel = (JPanel) bookManagment.findComponentAt(242,134);
        JScrollPane scrollPane = (JScrollPane) outperPanel.getComponent(3);
        JViewport viewPort = scrollPane.getViewport();
        JTabbedPane tabbedPane = (JTabbedPane) viewPort.getComponent(0);
        JPanel panel = (JPanel) tabbedPane.getComponent(0);
        
        JTextField bookID = (JTextField) panel.getComponent(13);
        JTextField bookISBN = (JTextField) panel.getComponent(7);
        JTextField bookTitle = (JTextField) panel.getComponent(8);
        JTextField bookAuthor = (JTextField) panel.getComponent(9);
        JTextField bookPublisher = (JTextField) panel.getComponent(10);
        JTextField bookEdition = (JTextField) panel.getComponent(11);
        JTextField bookCategory = (JTextField) panel.getComponent(12);
        JTextField bookCourse = (JTextField) panel.getComponent(17);
        JTextField bookDepartment = (JTextField) panel.getComponent(18);
        JTextField bookPageNumber = (JTextField) panel.getComponent(19);
        
        bookID.setText(book.getBookID());
        bookISBN.setText(book.getISBN());
        bookTitle.setText(book.getTitle());
        bookAuthor.setText(book.getAuthor());
        bookPublisher.setText(book.getPublisher());
        bookEdition.setText(book.getEdition());
        bookCategory.setText(book.getCategory());
        bookCourse.setText(book.getCourse());
        bookDepartment.setText(book.getDepartment());
        bookPageNumber.setText(String.valueOf(book.getNoOfPage()));
        
        delay();
        
        JPanel commandPanel = (JPanel) outperPanel.getComponent(1);
        JButton addButton = (JButton) commandPanel.getComponent(0);
        
        addButton.doClick();
        
        delay();
        
        bookManagment.dispose();
        assertFalse(bookManagment.isVisible());
        
        librarian.removeLibrarian();
        book.removeBook();
    }
    
    public void delay() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Login_Test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
