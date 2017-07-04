/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package top_down_integration_test;

import Boundary.LibrarianHomePage;
import Boundary.Login;
import Boundary.Rent;
import Boundary.Transaction;
import Entity.Book;
import Entity.Librarian;
import Entity.Patron;
import database_unit_test.Configuration;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import unit_test.Book_Test;
import unit_test.Login_Test;

/**
 *
 * @author Biruk Adera
 */
public class Rent_Book_Test {
    
    protected Librarian librarian;
    protected Patron patron;
    protected Book book;
    
    @Before
    public void initialize() {
        librarian = Configuration.librarian;
        librarian.registerLibrarian();
        
        patron = Configuration.patron;
        patron.registerPatron();
        
        book = Configuration.book;
    }
    
    @Test
    public void phase1_rentBook() {
        Login login = new Login();
        login.setVisible(true);
        
        JTextField usernameField = (JTextField) login.findComponentAt(293, 426);
        JPasswordField passwordField = (JPasswordField) login.findComponentAt(293, 476);
        JButton loginButton = (JButton) login.findComponentAt(503, 526);
         
        usernameField.setText(librarian.getName());
        passwordField.setText(librarian.getPassword());
        
        assertEquals(usernameField.getText(), librarian.getName());
        assertEquals(passwordField.getText(), librarian.getPassword());
        
        delay();
        loginButton.doClick();
        
        LibrarianHomePage librarianHomePage = login.getLibrarianHomePage();
        assertTrue(librarianHomePage.isVisible());
        JButton rentBookPage = (JButton) librarianHomePage.findComponentAt(44, 339);
        rentBookPage.doClick();
        
        Transaction transaction = librarianHomePage.getTransaction();
        assertTrue(transaction.isVisible());
        
        try {
            Robot robot = new Robot();
            robot.mouseMove(1000,350);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.delay(200);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
            robot.delay(200);
            
            
        }
        catch (AWTException ex) {
            Logger.getLogger(Book_Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        delay();
        
        JPanel pagePanel = (JPanel) transaction.findComponentAt(242,134);
        JTabbedPane tabbedPane = (JTabbedPane) pagePanel.getComponent(1);
        JPanel outerPanel = (JPanel) tabbedPane.getComponent(0);
        JPanel innerPanel = (JPanel) outerPanel.getComponent(0);
        
        JButton addToCart = (JButton) innerPanel.getComponent(2); 
        addToCart.doClick();
       
        delay();
        
        JButton rent = (JButton) innerPanel.getComponent(1);
        rent.doClick();
        
        Rent rentWindow = transaction.getRentWindow();
        assertTrue(rentWindow.isVisible());
        JPanel panel = (JPanel) rentWindow.findComponentAt(210, 50);
        
        JTextField root = (JTextField) panel.getComponent(3);
        root.setText(patron.getId());
        
        delay();     
        
        JButton done = (JButton) panel.getComponent(0);
        done.doClick();
        
        delay();
        
        rentWindow.dispose();
        transaction.dispose();
        
        librarian.removeLibrarian();
        patron.removePatron();
        
        assertFalse(librarianHomePage.isVisible());
        assertFalse(transaction.isVisible());
        assertFalse(rentWindow.isVisible());
    }
    
    public void delay() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Login_Test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
