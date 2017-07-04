/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unit_test;

import Boundary.AdministratorHomePage;
import Boundary.LibrarianHomePage;
import Boundary.Login;
import Entity.Administrator;
import Entity.Librarian;
import database_unit_test.Configuration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 *
 * @author Biruk Adera
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Login_Test {
    Librarian librarian;
    Administrator administrator;
    
    @Before
    public void initialize() {
        librarian = Configuration.librarian;
        administrator = Configuration.admin;
    }
    @Test
    public void phase1_LoginLibrarianFail() {
        Login login = new Login();
        login.setVisible(true);
        
        JTextField usernameField = (JTextField) login.findComponentAt(293, 426);
        JPasswordField passwordField = (JPasswordField) login.findComponentAt(293, 476);
        JButton loginButton = (JButton) login.findComponentAt(503, 526);
         
        usernameField.setText(librarian.getName());
        passwordField.setText(librarian.getPassword());
        
        delay();
        loginButton.doClick();
        
        LibrarianHomePage librarianHomePage = login.getLibrarianHomePage();
        Assert.assertNull(librarianHomePage);
        
        delay();
        login.dispose();
    }
    
    @Test
    public void phase2_loginLibrarianPass() {
        Login login = new Login();
        login.setVisible(true);
        
        JTextField usernameField = (JTextField) login.findComponentAt(293, 426);
        JPasswordField passwordField = (JPasswordField) login.findComponentAt(293, 476);
        JButton loginButton = (JButton) login.findComponentAt(503, 526);
        
        librarian.registerLibrarian();
        
        usernameField.setText(librarian.getName());
        passwordField.setText(librarian.getPassword());
        
        delay();
        loginButton.doClick();
        
        LibrarianHomePage librarianHomePage = login.getLibrarianHomePage();
        Assert.assertTrue(librarianHomePage.isVisible());
        
        delay();
        
        librarian.removeLibrarian();
        librarianHomePage.dispose();
        login.dispose();
    }
    
    @Test
    public void phase3_LoginAdminFail() {
        Login login = new Login();
        login.setVisible(true);
        
        JTextField usernameField = (JTextField) login.findComponentAt(293, 426);
        JPasswordField passwordField = (JPasswordField) login.findComponentAt(293, 476);
        JButton loginButton = (JButton) login.findComponentAt(503, 526);
         
        usernameField.setText(administrator.getName());
        passwordField.setText(administrator.getPassword());
        
        delay();
        loginButton.doClick();
        
        AdministratorHomePage administratorHomePage = login.getAdministratorHomePage();
        Assert.assertNull(administratorHomePage);
        
        delay();
        login.dispose();
        
        
    }
    
    @Test
    public void phase4_loginAdminPass() {
        Login login = new Login();
        login.setVisible(true);
        
        JTextField usernameField = (JTextField) login.findComponentAt(293, 426);
        JPasswordField passwordField = (JPasswordField) login.findComponentAt(293, 476);
        JButton loginButton = (JButton) login.findComponentAt(503, 526);
        
        administrator.registerAdministrator();
        
        usernameField.setText(administrator.getName());
        passwordField.setText(administrator.getPassword());
        
        delay();
        loginButton.doClick();
        
        AdministratorHomePage administratorHomePage = login.getAdministratorHomePage();
        Assert.assertTrue(administratorHomePage.isVisible());
        
        delay();
        
        administrator.removeAdministrator(administrator);
        administratorHomePage.dispose();
        login.dispose();
        
    }
    
    public void delay() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Login_Test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
