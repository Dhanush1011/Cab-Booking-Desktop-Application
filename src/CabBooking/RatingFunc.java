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
public class RatingFunc {
    
    public void updaterating(String carid, float rate)
    {
        ResultSet rst = null;
        try{
           
            String myUrl = "jdbc:mysql://localhost:3306/trial";
            Connection conn = DriverManager.getConnection(myUrl, "root", "242921326@pj");
            
            Statement st = conn.createStatement();
            Statement st1 = conn.createStatement();
            rst = st.executeQuery("SELECT * FROM driver");
            
            while(rst.next())
            {
                if(carid.equals(rst.getString("carid")))
                {
                    
                    float frate;
                    if(rate!=0)
                        frate = (rst.getFloat("rating") + rate)/2;
                    else
                        frate = rst.getFloat("rating");
                    
                    st1.executeUpdate("UPDATE driver SET rating = " + frate + " WHERE carid = '" + carid + "';");
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
