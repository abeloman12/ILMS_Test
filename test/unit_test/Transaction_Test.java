/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unit_test;

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
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 *
 * @author Biruk Adera
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Transaction_Test {
    protected Librarian librarian;
    protected Patron patron;
    
    @Before
    public void initialize() {
        librarian = Configuration.librarian;
        librarian.registerLibrarian();
        
        patron = Configuration.patron;
        patron.registerPatron();
    }
    
    @Test
    public void phase1_rentBook() {
        Transaction transcationPage = new Transaction(librarian);
        transcationPage.setVisible(true);
        
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
        
        JPanel pagePanel = (JPanel) transcationPage.findComponentAt(242,134);
        JTabbedPane tabbedPane = (JTabbedPane) pagePanel.getComponent(1);
        JPanel outerPanel = (JPanel) tabbedPane.getComponent(0);
        JPanel innerPanel = (JPanel) outerPanel.getComponent(0);
        
        JButton addToCart = (JButton) innerPanel.getComponent(2); 
        addToCart.doClick();
       
        delay();
        
        JButton rent = (JButton) innerPanel.getComponent(1);
        rent.doClick();
        
        Rent rentWindow = transcationPage.getRentWindow();
        JPanel panel = (JPanel) rentWindow.findComponentAt(210, 50);
        
        JTextField root = (JTextField) panel.getComponent(3);
        root.setText(patron.getId());
        
        delay();     
        
        JButton done = (JButton) panel.getComponent(0);
        done.doClick();
        
        delay();
        
        rentWindow.dispose();
        transcationPage.dispose();
        
        librarian.removeLibrarian();
        patron.removePatron();
    }
    
    public void delay() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Login_Test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
