/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database_unit_test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Biruk Adera
 */

@RunWith(Suite.class)

@Suite.SuiteClasses({
    Librarian_Test.class, Login_Test.class, Book_Test.class, Patron_Test.class
})
public class TestSuite {
    
}
