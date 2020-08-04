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
public class AddMoneyFunc {
    
    boolean addmoney(String amt, String userid, char[] p){
        String pwd = new String(p);
        ResultSet rst1 = null;
        int c=0;
        int count = 0;
        
        try{
           
            String myUrl = "jdbc:mysql://localhost:3306/trial";
            Connection conn1 = DriverManager.getConnection(myUrl, "root", "242921326@pj");
            
            Statement st1 = conn1.createStatement(); 
            rst1 = st1.executeQuery("SELECT * FROM trials");
            
            while(rst1.next())
            {
                if(userid.equals(rst1.getString("userid")) && pwd.equals(rst1.getString("pwd")))
                {
                    c = Integer.parseInt(rst1.getString("accountbal")) + Integer.parseInt(amt);
                    count = 1;
                }
            }
            
            rst1.close();
            conn1.close();
        }
        
        catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
        
        try{
           
            String myUrl = "jdbc:mysql://localhost:3306/trial";
            Connection conn1 = DriverManager.getConnection(myUrl, "root", "242921326@pj");
            
            Statement st1 = conn1.createStatement(); 
            
            
            st1.executeUpdate("UPDATE trials SET accountbal = '" + Integer.toString(c) + "' WHERE userid = '" + userid + "';");
            
            rst1.close();
            conn1.close();
        }
        
        catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
        
        if(count == 1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
