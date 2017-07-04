/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database_unit_test;

import Controller.JDBC;
import Entity.Patron;
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
public class Patron_Test {
    JDBC connection;
    Patron patron;
    
    @Before
    public void initialize() {
        connection = new JDBC();
        patron = Configuration.patron;
    }
    
    @Test
    public void phase1_addPatron() {
        
        System.out.println("[*] Testing : Add Patron!");
        System.out.println("\t[*] Adding : " + patron.getName());
        
        patron.registerPatron();
        Patron retrievedPatron = connection.returnPatron(Configuration.patronID);
        
        Assert.assertNotNull(retrievedPatron);
    }
    
    @Test
    public void phase2_removePatron() {
        System.out.println("[*] Testing : Remove Patron!");
        System.out.println("\t[*] Removing : " + patron.getName());
        
        patron.removePatron();
        Patron retrievedPatron = connection.returnPatron(Configuration.patronID);
        
        Assert.assertNull(retrievedPatron);
    }
}
