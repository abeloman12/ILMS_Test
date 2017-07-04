/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unit_test;

import Boundary.AdministratorHomePage;
import Boundary.PatronManagment;
import Entity.Administrator;
import Entity.Patron;
import com.sun.org.apache.xerces.internal.utils.ConfigurationError;
import database_unit_test.Configuration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
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
public class Patron_Test {
    
    protected Administrator administrator;
    protected Patron patron;
    
    @Before
    public void initialize() {
        administrator = Configuration.admin;
        administrator.registerAdministrator();
        
        patron = Configuration.patron;
    }
    
    @Test
    public void phase1_addPatron() {
        AdministratorHomePage adminPage = new AdministratorHomePage(administrator);
        adminPage.setVisible(true);
        
        JButton button = (JButton) adminPage.findComponentAt(1034, 406);
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
        
        JPanel removePanel = (JPanel) patronManagment.findComponentAt(152, 577);
        JButton addButton = (JButton) removePanel.getComponent(0);
        
        delay();
        
        addButton.doClick();
        
        delay();
        
        patronManagment.dispose();
        administrator.removeAdministrator(administrator);
    }
    
    public void delay() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Login_Test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
