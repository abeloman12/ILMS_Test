/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unit_test;

import Boundary.BookManagment;
import Boundary.LibrarianHomePage;
import Entity.Book;
import Entity.Librarian;
import database_unit_test.Configuration;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JViewport;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 *
 * @author Biruk Adera
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Book_Test {
    protected Book book;
    protected Librarian librarian;
    
    @Before
    public void initialize() {
        librarian = Configuration.librarian;
        librarian.registerLibrarian();
        
        book = Configuration.book;
    }
    
    @Test
    public void phase1_addBook() {
        LibrarianHomePage librarianHomePage = new LibrarianHomePage(librarian);
        librarianHomePage.setVisible(true);
        
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
        
    }
    @Test
    public void phase2_removeBook() {
        LibrarianHomePage librarianHomePage = new LibrarianHomePage(librarian);
        librarianHomePage.setVisible(true);
        
        JButton bookManagmentButton = (JButton) librarianHomePage.findComponentAt(242, 339);
        bookManagmentButton.doClick();
        
        BookManagment bookManagment = librarianHomePage.getBookManagment();
        
        JPanel outerPanel = (JPanel) bookManagment.findComponentAt(242,134);
        JScrollPane outerScrollPane = (JScrollPane) outerPanel.getComponent(4);
        JViewport outerViewPort = outerScrollPane.getViewport();
        JScrollPane innerScrollPane = (JScrollPane) outerViewPort.getComponent(0);
        JViewport innerScroll = innerScrollPane.getViewport();
        JTable table = (JTable) innerScroll.getComponent(0);
        
        table.setRowSelectionInterval(0, 0);

        try {
            Robot robot = new Robot();
            robot.mouseMove(table.getX(), table.getY());
            
            robot.mouseMove(1000,350);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.delay(200);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
            robot.delay(200);
            
            
        } catch (AWTException ex) {
            Logger.getLogger(Book_Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        delay();
        
        JPanel commandPanel = (JPanel) outerPanel.getComponent(1);
        JButton removeButton = (JButton) commandPanel.getComponent(1);
        
        removeButton.doClick();
        
        delay();
        
        bookManagment.dispose();
        librarian.removeLibrarian();
    }
    
    public void delay() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Login_Test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
