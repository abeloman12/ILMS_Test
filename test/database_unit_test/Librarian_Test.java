/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database_unit_test;

import Controller.JDBC;
import Entity.Book;
import Entity.Librarian;
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
public class Librarian_Test {
    
    private JDBC connection;
    private Book book;
    private Patron patron;
    private Librarian librarian;
    
    @Before
    public void setup() {    
        connection = new JDBC();
        
        book = Configuration.book;
        book.registerBook();
        
        patron = Configuration.patron;
        patron.registerPatron();
        
        librarian = Configuration.librarian;
        librarian.registerLibrarian();
    }
    
    @Test
    public void phase1_lendBookPass() {
        System.out.println("[*] Testing : Lend Book");
        
        System.out.println("\n\t[*] Book : " + book.getTitle());
        System.out.println("\t[*] Librarian : " + librarian.getName());
        System.out.println("\t[*] Patron : " + patron.getName() + "\n");
        
        System.out.println("\t[*] Lending Available Book");
        boolean initailBookStatus = book.isRented();
        
        System.out.println("\t[*] Lending Book...");
        librarian.lendBook(book, patron);
        
        Assert.assertNotEquals(initailBookStatus, book.isRented());
    }
    
    @Test
    public void phase2_lendBookFail() {
        System.out.println("\t[*] Lending Unavailable Book");
        boolean initailBookStatus = book.isRented();
        
        librarian.lendBook(book, patron);
        
        Assert.assertEquals(initailBookStatus, book.isRented());
    }
    
    @Test
    public void phase3_returnBookPass() {
        System.out.println("[*] Testing : Return Book!");
        
        System.out.println("\n\t[*] Book : " + book.getTitle());
        System.out.println("\t[*] Librarian : " + librarian.getName());
        System.out.println("\t[*] Patron : " + patron.getName() + "\n");
        
        
        System.out.println("\t[*] Returning Rented Book");
        boolean initailBookStatus = book.isRented();
        
        System.out.println("\t[*] Returning Book...");
        librarian.returnBook(book, patron);
        
        Assert.assertNotEquals(initailBookStatus, book.isRented());
    }
    

    @Test
    public void phase4_returnBookFail() {
        System.out.println("\t[*] Returning Available Book");
        boolean initailBookStatus = book.isRented();
        
        librarian.returnBook(book, patron);
        
        Assert.assertEquals(initailBookStatus, book.isRented());
        
        book.removeBook();
        librarian.removeLibrarian();
        patron.removePatron();
    }
    
}
