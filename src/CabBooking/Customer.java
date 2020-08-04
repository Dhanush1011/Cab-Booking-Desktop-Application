/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CabBooking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author karupakula
 */
public class Customer {
    
    String userid;
    String username;
    String pwd;
    String email;
    String phno;
    String accbal;
    int fare,dist;
    
    public void getcontent(String userid){
        
        ResultSet rst = null;
                
        try{
           
            String myUrl = "jdbc:mysql://localhost:3306/trial";
            Connection conn = DriverManager.getConnection(myUrl, "root", "242921326@pj");
            
            Statement st = conn.createStatement(); 
            rst = st.executeQuery("SELECT * FROM trials");
            
            while(rst.next())
            {
                if(userid.equals(rst.getString("userid")))
                {
                    this.userid = userid;
                    this.email = rst.getString("email");
                    this.username = rst.getString("username");
                    this.phno = rst.getString("phno");
                    this.pwd = rst.getString("pwd");
                    this.accbal = rst.getString("accountbal");
                    break;
                }
            }
            
            rst.close();
            conn.close();
        }
        
        catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
    }
    
    public void calfare(String from, String to){
        
        ResultSet rst = null;
        ResultSet rst1 = null;
           
        int start=0, stop=0;
        fare = 0;
        dist = 0;
        
        try{
           
            String myUrl = "jdbc:mysql://localhost:3306/trial";
            Connection conn = DriverManager.getConnection(myUrl, "root", "242921326@pj");
            
            Statement st = conn.createStatement(); 
            rst = st.executeQuery("SELECT * FROM dist");
            
            while(rst.next())
            {
                if(from.equals(rst.getString("fromloc")))
                {
                    fare = rst.getInt(to)*5;
                    dist = rst.getInt(to);
                }
            }
            
            rst.close();
            conn.close();
        }
        
        catch (Exception e) {
            System.err.println("Got an/ exception!");
            System.err.println(e.getMessage());
        }
        

    }
    
    public void reducebalance(int farered)
    {
        ResultSet rst = null;
        try{
           
            String myUrl = "jdbc:mysql://localhost:3306/trial";
            Connection conn = DriverManager.getConnection(myUrl, "root", "242921326@pj");
            
            Statement st = conn.createStatement(); 
            rst = st.executeQuery("SELECT * FROM trials");
            Statement st1 = conn.createStatement();
            while(rst.next())
            {
                if(rst.getString("userid").equals(this.userid))
                {
                    int c = Integer.parseInt(this.accbal) - farered;
                    st1.executeUpdate("UPDATE trials SET accountbal = '" + Integer.toString(c) + "' WHERE userid = '" + this.userid + "';");
                    this.accbal = Integer.toString(c);
                }
            }
            
            rst.close();
            conn.close();
            }
        
        catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
    }
}
