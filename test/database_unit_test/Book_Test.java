/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database_unit_test;

import Controller.JDBC;
import org.junit.Test;
import Entity.Book;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

/**
 *
 * @author Biruk Adera
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Book_Test {
    
    JDBC connection;
    Book book;
    
    public Book_Test() {
        connection = new JDBC();
        book = Configuration.book;
    }
    
    @Test
    public void phase1_addBook() {
        System.out.println("[*] Testing : Add Book!");
        System.out.println("\t[*] Adding : " + book.getTitle());
        
        book.registerBook();
        Book bookRetrieved = connection.returnBook(Configuration.bookID);
        
        Assert.assertNotNull(bookRetrieved);
    }
    
    @Test
    public void phase2_removeBook() {
        System.out.println("[*] Testing : Remove Book!");
        System.out.println("\t[*] Removing : " + book.getTitle());
        
        book.removeBook();
        Book bookRetrieved = connection.returnBook(Configuration.bookID);
        
        Assert.assertNull(bookRetrieved);
       
    }
    
}
