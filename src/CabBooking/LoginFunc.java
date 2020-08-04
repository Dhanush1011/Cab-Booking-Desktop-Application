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
public class LoginFunc {
    
    boolean checkuser(String userid, char pwdf[]){
        
        String pwd = new String(pwdf);
        ResultSet rst = null;
        int count = 0;
        
        try{
           
            String myUrl = "jdbc:mysql://localhost:3306/trial";
            Connection conn = DriverManager.getConnection(myUrl, "root", "242921326@pj");
            
            Statement st = conn.createStatement(); 
            rst = st.executeQuery("SELECT * FROM trials");
            
            while(rst.next())
            {
                if(userid.equals(rst.getString("userid")) && pwd.equals(rst.getString("pwd")))
                {
                    count = 1;
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
        
        if(count == 1)
            return true;
        else
            return false;
    }
}
