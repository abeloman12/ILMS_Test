/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web_unit_test;

import java.lang.reflect.Method;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import web_unit_test.Scrapper;

/**
 *
 * @author Abel
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Comment_Test {
    Scrapper scrapper; 
    int numberOfComments;
    String bookId;
    String comment;
   
    public Comment_Test(){
        bookId = "123";
        comment = "This is a new Comment";
        scrapper =  new Scrapper("http://localhost:82/ilms_web/");
        scrapper.login("Abel Mandefro", "htconem7");
        numberOfComments = scrapper.countComments(bookId);
    }
    
    @Test
    public void a_testAddComment(){
        System.out.println("[*] Testing : Add Comment");
        System.out.println("\t[*] Adding Comment : " + comment);
        scrapper.addComment(comment, bookId);
        int newNumberOfComments = scrapper.countComments(bookId);
        scrapper.close();
        
        assertTrue(numberOfComments + 1 == newNumberOfComments);
            
    }
    
}
