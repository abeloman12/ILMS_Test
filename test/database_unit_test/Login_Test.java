/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database_unit_test;

import Controller.JDBC;
import Entity.Administrator;
import Entity.Book;
import Entity.Librarian;
import Entity.Patron;
import Tools.Tools;
import org.junit.Assert;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
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
public class Login_Test {
    
    JDBC connection;
    
    @Before
    public void initialize() {
        connection = new JDBC();
    }
    
    @Test
    public void phase1_loginLibrarianFail() {
        System.out.println("[*] Testing : Login For Librarian");
        System.out.println("\t[*] Testing For Unregistered Librarian.");
        
        Librarian librarian = Configuration.librarian;
        Object user = connection.login(librarian.getName(), librarian.getPassword());
        assertNull(user);
    }
    
    @Test 
    public void phase2_loginLibrarianSuccess() {
        System.out.println("\t[*] Registering Librarian...");
        
        Librarian librarian = Configuration.librarian;
        librarian.registerLibrarian();
        
        System.out.println("\t[*] Testing Login...");
        Object user = connection.login(librarian.getName(), librarian.getPassword());
        
        assertNotNull(user);
        assertTrue(user instanceof Librarian);
        
        librarian.removeLibrarian();
    }
    
    @Test
    public void phase3_loginAdminFail() {
        System.out.println("[*] Testing : Login For Administrator");
        System.out.println("\t[*] Testing For Unregistered Administrator.");
        
        Administrator admin = Configuration.admin;
        Object user = connection.login(admin.getName(), admin.getPassword());
        assertNull(user);
    }
    
    @Test
    public void phase4_loginAdminSuccess() {
        System.out.println("\t[*] Registering Administrator...");
        
        Administrator admin = Configuration.admin;
        admin.registerAdministrator();
        
        System.out.println("\t[*] Testing Login...");
        Object user = connection.login(admin.getName(), admin.getPassword());
        
        assertNotNull(user);
        assertTrue(user instanceof Administrator);
        
        admin.removeAdministrator(admin);
    }
    
}
