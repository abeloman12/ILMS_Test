/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web_unit_test;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

/**
 *
 * @author Abel
 */
public class Scrapper {
    WebDriver driver;
    boolean isLoggedIn;
    boolean isInCommentSection;
    
    public Scrapper(String link){
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        this.driver.get(link);
    }
    
    public boolean login(String userName, String passWord){
        this.driver.findElement(By.id("user")).sendKeys(userName);
        WebElement password = this.driver.findElement(By.id("pass"));
        password.sendKeys(passWord);
        password.submit();
        
        try{
            if(this.driver.findElement(By.id("sid")).isDisplayed()) isLoggedIn = true;
            return true;
        }
        catch(NoSuchElementException nsef){
            isLoggedIn = false;
            System.out.println("Login not Successful!!!");
            return false;    
        }
        
          
    }
    
    public void logout(){
//        this.driver.findElement(By.id("logout")).click();
//        isLoggedIn = false;
    }
    
    public ArrayList<String> searchByAuthor(String keyWord) throws InterruptedException{
        if(isLoggedIn){
            ArrayList<String> listOfTitles = new ArrayList<String>();
            Select dropDown = new Select(driver.findElement(By.id("select")));
            dropDown.selectByVisibleText("Author");
            this.search(keyWord);
            String page = "http://localhost:82/ilms_web/index.php?searchType=Author&searchKey="+keyWord+"&search=Search";
            this.driver.navigate().to(page);
            try{
                List<WebElement> titles = this.driver.findElements(By.className("titleLinkForResult"));
                
                for(int i = 0; i < titles.size(); i++){
                    listOfTitles.add(titles.get(i).getText());
                    
                }
                return listOfTitles;
                
            }
            catch(NoSuchElementException e){
                return listOfTitles;
            }
            
        }
        
        else{
            System.out.println("User not Loggedin, Cannot Execute Search");
            System.exit(1);
            return null;
        }
        
        
    }
    
    public ArrayList<String> searchByTitle(String keyWord) throws InterruptedException{
        if(isLoggedIn){
            ArrayList<String> listOfTitles = new ArrayList<String>();
            Select dropDown = new Select(driver.findElement(By.id("select")));
            dropDown.selectByVisibleText("Title");
            this.search(keyWord);
            String page = "http://localhost:82/ilms_web/index.php?searchType=Title&searchKey="+keyWord+"&search=Search";
            this.driver.navigate().to(page);
            try{
                List<WebElement> titles = this.driver.findElements(By.className("titleLinkForResult"));
                
                for(int i = 0; i < titles.size(); i++){
                    listOfTitles.add(titles.get(i).getText());
                    
                }
                return listOfTitles;
                
            }
            catch(NoSuchElementException e){
                return listOfTitles;
            }
            
        }
        
        else{
            System.out.println("User not Loggedin, Cannot Execute Search");
            System.exit(1);
            return null;
        }
        
    }
    
    public ArrayList<String> searchByISBN(String keyWord) throws InterruptedException{
        if(isLoggedIn){
            ArrayList<String> listOfTitles = new ArrayList<String>();
            Select dropDown = new Select(driver.findElement(By.id("select")));
            dropDown.selectByVisibleText("ISBN");
            this.search(keyWord);            
            String page = "http://localhost:82/ilms_web/index.php?searchType=iSBN&searchKey="+keyWord+"&search=Search";
            this.driver.navigate().to(page);
            try{
                List<WebElement> titles = this.driver.findElements(By.className("titleLinkForResult"));
                
                for(int i = 0; i < titles.size(); i++){
                    listOfTitles.add(titles.get(i).getText());
                    
                }
                return listOfTitles;
                
            }
            catch(NoSuchElementException e){
                return listOfTitles;
            }
            
        }
        
        else{
            System.out.println("User not Loggedin, Cannot Execute Search");
            System.exit(1);
            return null;
        }
        
    }
    
    public void addComment(String comment, String bookId){
        if(isLoggedIn){
            try{
                this.driver.navigate().to("http://localhost:82/ilms_web/index.php?page=bookDetail&bookID="+bookId);
                if(this.driver.findElement(By.id("displayDetailed")).isDisplayed()){
                    //isInCommentSection = true;                
                    driver.findElement(By.id("myComment")).sendKeys(comment);
                    driver.findElement(By.id("commentButton")).click();

                }
            }
            catch(NoSuchElementException e){
                //isInCommentSection = false;
            }
        }
        
        else{
            System.out.println("User not Loggedin, Cannot Add Comment");
            System.exit(1);                
       }
        
    }
    
    public boolean removeComment(String bookId) throws InterruptedException{
        if(isLoggedIn){
            try{
               this.driver.navigate().to("http://localhost:82/ilms_web/index.php?page=bookDetail&bookID="+bookId);
               this.driver.navigate().refresh();
               
               List<WebElement> deleteButtons = driver.findElements(By.className("delete"));
               WebElement finalDelete = null;
               for(WebElement deleteButton : deleteButtons){
                   finalDelete = deleteButton;
               }
               finalDelete.click();
               this.driver.navigate().refresh();
               
               return true;
               
            }
            catch(NoSuchElementException e){
                return false;
            }
        }
        
        else{
            return false;
        }
    }
    
    public int countComments(String bookId){
         try{
            this.driver.navigate().to("http://localhost:82/ilms_web/index.php?page=bookDetail&bookID="+bookId);
            if(this.driver.findElement(By.id("displayDetailed")).isDisplayed()){
                //isInCommentSection = true;
                List<WebElement> comments = driver.findElements(By.className("comments"));
                
                
                return comments.size();
            }
        }
        catch(NoSuchElementException e){
            System.out.println("Element Not Found!!!");
            System.exit(1);
            return -1;
        }
        return -1;
    }
    
    public void search(String keyWord) throws InterruptedException{
        this.driver.findElement(By.id("searchInput")).sendKeys(keyWord);
        driver.findElement(By.name("search")).click();        
        
        try{
            
        }
        catch(NoSuchElementException e){
            System.out.println("Element Not Found!!!");
            System.exit(1);
        }
    }
    
    public String reserve(String bookId) throws InterruptedException{
        if(isLoggedIn){
            this.driver.navigate().to("http://localhost:82/ilms_web/index.php?page=bookDetail&bookID="+bookId);
            
            if(driver.findElement(By.id("reserveInput")).isEnabled()){
                return "Book is Reserved";
            }
            
            else if(!(driver.findElement(By.id("reserveInput")).isEnabled())){
                return "Book already Reserved";
            }
        
            else{
                System.exit(1);
                return "Error";
            }            
        }
        
        else{
            return "User not Logged In";
        }
        
    }
    
    public void close(){
        this.driver.close();
    }
    
    
    
}
