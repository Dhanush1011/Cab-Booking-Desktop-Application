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
public class HomeFunc {
    
    static int tripcount ;
    
    public void trip()
    {
        if(tripcount < 3)
           tripcount++;
        else
        {
            ResultSet rst = null;
            String loc[] = {"BITSH","Thumkunta","Secunderabad","Patny","Rasoolpura"};
            int i=0;
            try{
           
            String myUrl = "jdbc:mysql://localhost:3306/trial";
            Connection conn = DriverManager.getConnection(myUrl, "root", "242921326@pj");
            
            Statement st = conn.createStatement(); 
            rst = st.executeQuery("SELECT * FROM driver");
            Statement st1 = conn.createStatement();
            while(rst.next())
            {
                st1.executeUpdate("UPDATE driver SET nearloc = '" + loc[i] + "' WHERE carid = '" + rst.getString("carid") + "';");
                i++;
            }
            
            rst.close();
            conn.close();
            }
        
            catch (Exception e) {
                System.err.println("Got an exception!");
                System.err.println(e.getMessage());
            }
            tripcount = 0;
        }
    }
}
