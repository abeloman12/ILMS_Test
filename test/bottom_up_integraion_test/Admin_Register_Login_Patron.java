/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bottom_up_integraion_test;

import Boundary.AdministratorHomePage;
import Boundary.Login;
import Boundary.PatronManagment;
import Controller.JDBC;
import Entity.Administrator;
import Entity.Patron;
import database_unit_test.Configuration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import unit_test.Login_Test;
import web_unit_test.Scrapper;

/**
 *
 * @author Biruk Adera
 */
public class Admin_Register_Login_Patron {
    protected Administrator administrator;
    protected Patron patron;
    JDBC connection;
    
    @Before
    public void initialize() {
        Administrator newAdministrator = Configuration.admin;
        newAdministrator.registerAdministrator();
        
        patron = Configuration.patron;
        connection = new JDBC();
    }
    
    @Test
    public void phase1_testAddPatron() {
        administrator = connection.returnAdministrator(Configuration.administratorID);
        assertNotNull(administrator);
        assertTrue(administrator instanceof Administrator);
        
        Login login = new Login();
        login.setVisible(true);
        
        JTextField usernameField = (JTextField) login.findComponentAt(293, 426);
        JPasswordField passwordField = (JPasswordField) login.findComponentAt(293, 476);
        JButton loginButton = (JButton) login.findComponentAt(503, 526);
         
        usernameField.setText(administrator.getName());
        passwordField.setText(Configuration.admin.getPassword());
        
        assertEquals(usernameField.getText(), administrator.getName());
        assertEquals(passwordField.getText(), Configuration.admin.getPassword());
        
        delay();
        loginButton.doClick();
        
        AdministratorHomePage adminPage = login.getAdministratorHomePage();
        assertTrue(adminPage.isVisible());
        
        JButton button = (JButton) adminPage.findComponentAt(1034, 406);
        delay();
        button.doClick();
        
        PatronManagment patronManagment = adminPage.getPatronManagment();
        assertTrue(patronManagment.isVisible());
        JPanel panel = (JPanel) patronManagment.findComponentAt(352, 577);
        
        JTextField patronID = (JTextField) panel.getComponent(5);
        JTextField patronName = (JTextField) panel.getComponent(6);
        JTextField patronEmail = (JTextField) panel.getComponent(7);
        JTextField patronDepartment = (JTextField) panel.getComponent(8);
        
        patronID.setText(patron.getId());
        patronName.setText(patron.getName());
        patronEmail.setText(patron.getEmail());
        patronDepartment.setText(patron.getDepartment());
        
        assertEquals(patronID.getText(), patron.getId());
        assertEquals(patronName.getText(), patron.getName());
        assertEquals(patronEmail.getText(), patron.getEmail());
        assertEquals(patronDepartment.getText(), patron.getDepartment());
        
        JPanel removePanel = (JPanel) patronManagment.findComponentAt(152, 577);
        JButton addButton = (JButton) removePanel.getComponent(0);
        
        delay();
        addButton.doClick();
        delay();
        
        patronManagment.dispose();
        administrator.removeAdministrator(administrator);
        
        assertFalse(patronManagment.isVisible());
        
        delay();
        
        Scrapper scrapper = new Scrapper("http://localhost:82/ilms_web/");
        scrapper.login(patron.getName(), patron.getPassword());
        
    }
    
    public void delay() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Login_Test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
