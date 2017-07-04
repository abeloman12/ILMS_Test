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
public class Book {
    private String bookID;
    private boolean isReserved;
        
    public Book(String bookID, boolean isReserved){
        this.bookID = bookID;
        this.isReserved = isReserved;
        
    }
    
    public String getBookID(){
        return bookID;
    }
    public boolean getIsReserved(){
        return isReserved;
    }
    
    public void setBookID(String bookID){
        this.bookID = bookID;
    }
    public void setIsReserved(boolean isReserved){
        this.isReserved = isReserved;
    }
    
    
}
