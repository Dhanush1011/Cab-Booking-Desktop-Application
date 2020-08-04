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
public class SignupFunc {
    
    public boolean isempty(String username, String userid, String pwd, String email, String phno) {
        if(username.equals("") || userid.equals("") || pwd.equals("") || email.equals("") || phno.equals(""))
        {
            return true;
        }
        else
            return false;
    }
    
    public boolean isuserid(String userid)
    {
        ResultSet rst = null;
        int count = 0;
        try
        {   
            
            // create a mysql database connection
            String myUrl = "jdbc:mysql://localhost:3306/trial";
            Connection conn = DriverManager.getConnection(myUrl, "root", "242921326@pj");
            
            Statement st = conn.createStatement(); 
            rst = st.executeQuery("SELECT userid FROM trials");  
            
            while(rst.next())
            {
                if(userid.equals(rst.getString("userid")))
                {
                    count++;
                }
            }
            rst.close();
            conn.close();
          
        }
        
        catch (Exception e)
        {
          System.err.println("Got an exception!");
          System.err.println(e.getMessage());
        }
        
        
        if(count==0)
           {
               return true;
           }
        else
           {
               return false;
           }    
    }
    
    public boolean isemail(String email)
    {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }
    
    public boolean isphno(String phno)
    {
        String regex = "(0/91)?[7-9][0-9]{9}";
        return phno.matches(regex);
    }
    
    public void writesql(String username, String userid, String pwd, String email, String phno){
        try
        {
          // create a mysql database connection
          String myUrl = "jdbc:mysql://localhost:3306/trial";
          Connection conn = DriverManager.getConnection(myUrl, "root", "242921326@pj");
    
            Statement st = conn.createStatement(); 
            st.executeUpdate("INSERT INTO trials " + 
                "VALUES ('" + username + "','" + userid + "','" + pwd + "','" + email + "','" + phno + "','0');");       
          conn.close();
        }
        
        catch (Exception e)
        {
          System.err.println("Got an exception!");
          System.err.println(e.getMessage());
        }
    }
}
