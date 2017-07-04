/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web_unit_test;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Abel
 */
public class JDBC {
    private Connection con;
    private Statement st;
    ResultSet rs;
    String result;
    
    private static String dbName = "ilms";
        
  
    public JDBC()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dbName + "?zeroDateTimeBehavior=convertToNull", "root", "");
            st = con.createStatement();
        }
        catch (ClassNotFoundException | SQLException e)
        {
            System.out.print("Error");
        }
    }
    
    public ResultSet searchBookByTitle(String searchTerm)
    {
        try
        {         
            PreparedStatement stmt = con.prepareStatement("Select BookID, ISBN, Title, Author from Book where Title LIKE ?");
            stmt.setString(1, searchTerm + "%");
            
            rs = stmt.executeQuery();
            return rs;            
        }
        
        catch (Exception e)
        {
           // JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public ResultSet searchBookByAuthor(String searchTerm)
    {
        try
        {         
            PreparedStatement stmt = con.prepareStatement("Select BookID, ISBN, Title, Author from Book where Author = ?");
            stmt.setString(1, searchTerm);
            
            rs = stmt.executeQuery();
            return rs;
        }
        
        catch (Exception e)
        {
            //JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public ResultSet searchBookByISBN(String searchTerm)
    {
        try
        {         
            PreparedStatement stmt = con.prepareStatement("Select BookID, ISBN, Title, Author from Book where ISBN = ?");
            stmt.setString(1, searchTerm);
            
            rs = stmt.executeQuery();
            return rs;
        }
        
        catch (Exception e)
        {
            //JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public ResultSet getBookById(String bookId)
    {
        try
        {         
            PreparedStatement stmt = con.prepareStatement("Select BookID, is_Reserved from Book where BookID = ?");
            stmt.setString(1, bookId);
            
            rs = stmt.executeQuery();
            return rs;
        }
        
        catch (Exception e)
        {
            
            return null;
        }
    }
}
