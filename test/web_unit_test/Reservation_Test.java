/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web_unit_test;

import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 *
 * @author Abel
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Reservation_Test {
    Scrapper scrapper;
    String bookId;
    
    @Before
        public void initialize() {
            scrapper =  new Scrapper("http://localhost:82/ilms_web/");
            scrapper.login("Abel Mandefro", "htconem7");
        }
    
   
    public Reservation_Test(){
        bookId = "11111";
    }
    
    @Test
    public void a_testReservation(){
        System.out.println("[*] Testing : Book Reservation");
        System.out.println("\t[*] Reserving Book");
        String scrapperReserved = null;
        try{
           scrapperReserved = scrapper.reserve(bookId);
           scrapper.close();
        }
        catch(InterruptedException e){
            System.out.println("Interruption Error Occured!!!");
            System.exit(1);
        }
        boolean isReserved = isReserved(bookId);
        
        if(isReserved == false){
            assertEquals(scrapperReserved, "Book is Reserved");
        }
        
        else if(isReserved == true){
            assertEquals(scrapperReserved, "Book already Reserved");
        }
            
    }
    
    public boolean isReserved(String bookId){
        ArrayList<Book> books = new ArrayList<Book>();
        JDBC a = new JDBC();
        ResultSet rs = a.getBookById(bookId);
        try{
            while(rs.next()){
                Book book = new Book(rs.getString("BookID"),rs.getBoolean("is_Reserved"));
                books.add(book);
            }
        }        
        catch(IndexOutOfBoundsException e){
            System.out.println("Index Out of Bound, Try Entering a new Book Id");
            System.exit(1);
        } 
        
        catch (SQLException ex) {
            System.out.println("SQL Exception Occured!");
            System.exit(1);
        }
        
        
       return(books.get(0).getIsReserved());
    }
    
    
}
