/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web_unit_test;

/**
 *
 * @author Abel
 */

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({ 
  Login_Test.class, Search_Count_Test.class, Reservation_Test.class, Comment_Test.class
})
public class TestSuite {
    
}
