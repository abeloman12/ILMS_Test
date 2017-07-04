/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web_unit_test;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized;

/**
 *
 * @author Abel
 */
@RunWith(Parameterized.class)
public class Login_Test {
    String userName;
    String passWord;
    boolean expectedResult;
    Scrapper scrapper;
    
    @Before
   public void initialize() {
      scrapper =  new Scrapper("http://localhost:82/ilms_web/");
   }
   
   public Login_Test(String userName, String passWord, boolean expectedResult){
       this.userName = userName;
       this.passWord = passWord;
       this.expectedResult = expectedResult;
   }
   
   @Parameterized.Parameters
   public static Collection accounts() {
      return Arrays.asList(new Object[][] {
         { "Abel Mandefro", "htconem7", true },
         { "Yabetse Genene", "xcxinsignia", true },
         { "Hello world", "12345", false },        
      });
   }
   
   @Test
   public void testLogin() {
      System.out.println("[*] Checking Login for " + userName);
      assertEquals(expectedResult, 
      scrapper.login(userName, passWord));
      scrapper.close();
   }




}
