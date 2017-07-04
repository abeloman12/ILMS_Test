/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functional_test;

import unit_test.*;
import web_unit_test.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;


/**
 *
 * @author Biruk Adera
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    Book_Test.class, unit_test.Login_Test.class, Patron_Test.class, Transaction_Test.class,
    Comment_Test.class, web_unit_test.Login_Test.class
})
public class TestSuite {
    
}
