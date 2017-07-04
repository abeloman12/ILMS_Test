/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database_unit_test;

import Entity.Administrator;
import Entity.Book;
import Entity.Librarian;
import Entity.Patron;

/**
 *
 * @author Biruk Adera
 */
public class Configuration {
    
    public static String bookID = "9999";
    public static String patronID = "9999";
    public static String librarianID = "9999";
    public static String administratorID = "9999";
    
    public static Book book = new Book("Software Testing: Principles and Practices",
                "9780198061847", 
                bookID,
                null,
                "OUP India",
                "Naresh Chauhan",
                "1st Edition",
                "Computer Science",
                325,
                false,
                false,
                0.0f,
                "Software Testing and Verfication",
                "ITSC",
                "Software Testing is specially developed to serve as a text book for "
                        + "the undergraduate and postgraduate students of Computer Science "
                        + "Engineering and Information Technology."
        );
    
    public static Librarian librarian = new Librarian(
            "Biruk Adera",
            librarianID,
            "1234567890",
            "aderabiruk@gmail.com"
    );
    
    public static Patron patron = new Patron(
            "Yabetse Genene",
            patronID,
            "1234567890",
            null,
            "yabetsegenene@gmail.com",
            "ITSC"
    );
    
    public static Administrator admin = new Administrator(
            administratorID,
            "Nathan Tsegaye",
            "1234567890",
            "nathantsegaye@gmail.com"
    );
    
}
