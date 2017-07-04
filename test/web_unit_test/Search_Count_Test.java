/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web_unit_test;

import web_unit_test.Scrapper;
import java.util.ArrayList;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
/**
 *
 * @author Abel
 */

public class Search_Count_Test {
        Scrapper scrapper; 
        int numberOfBooksByTitle;
        int numberOfBooksByAuthor;
        int numberOfBooksByIsbn;
        String keyWordForTitle;
        String keyWordForAuthor;
        String keyWordForIsbn;
        
        @Before
        public void initialize() {
            scrapper =  new Scrapper("http://localhost:82/ilms_web/");
            scrapper.login("Abel Mandefro", "htconem7");
        }

        
        public Search_Count_Test(){            
            numberOfBooksByTitle = 3;
            numberOfBooksByAuthor = 2;
            numberOfBooksByIsbn = 1;
            keyWordForTitle = "Mob";
            keyWordForAuthor = "Kate Williams";
            keyWordForIsbn = "8907897";
            
        }
        
         @Test
        public void searchByTitle() {
            System.out.println("[*] Testing : Search Book By Title");
            System.out.println("\t[*] Searching for title that starts with : " + keyWordForTitle);
            ArrayList<String> result = null;
            try{
                result = scrapper.searchByTitle(keyWordForTitle); 
                scrapper.close();
            }
            catch(InterruptedException e){
                System.out.println("Interruption Error Occured!!!");
                System.exit(1);
            }
                       

            Assert.assertEquals(numberOfBooksByTitle, result.size());
        }
    
        @Test
        public void searchByAuthor() {
            System.out.println("[*] Testing : Search Book By Author");
            System.out.println("\t[*] Searching for books of Author : " + keyWordForAuthor);

            ArrayList<String> result = null;
            try{
                result = scrapper.searchByAuthor(keyWordForAuthor); 
                scrapper.close();
            }
            catch(InterruptedException e){
                System.out.println("Interruption Error Occured!!!");
                System.exit(1);
            }
                       

            Assert.assertEquals(numberOfBooksByAuthor, result.size());

        }
        
        @Test
        public void searchByIsbn() {
            System.out.println("[*] Testing : Search Book By ISBN");
            System.out.println("\t[*] Searching for books with ISBN : " + keyWordForIsbn);

            ArrayList<String> result = null;
            try{
                result = scrapper.searchByISBN(keyWordForIsbn); 
                scrapper.close();
            }
            catch(InterruptedException e){
                System.out.println("Interruption Error Occured!!!");
                System.exit(1);
            }
                       

            Assert.assertEquals(numberOfBooksByIsbn, result.size());

        }
}
