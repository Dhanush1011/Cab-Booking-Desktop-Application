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
import java.util.Arrays;
import java.util.Comparator;

/**
 *
 * @author karupakula
 */
public class Driver {
    String dname;
    String car;
    String carid;
    String dphno;
    String rating;
    int c;
    
    public void cald(String from){
        
        ResultSet rst = null;
        
        String nearlocs[][] = new String[5][2];

        try{
           
            String myUrl = "jdbc:mysql://localhost:3306/trial";
            Connection conn = DriverManager.getConnection(myUrl, "root", "242921326@pj");
            
            Statement st = conn.createStatement(); 
            rst = st.executeQuery("SELECT * FROM dist");
            
            while(rst.next())
            {
                if(from.equals(rst.getString("fromloc")))
                {
                   nearlocs[0][0] = Integer.toString(rst.getInt("BITSH"));
                   nearlocs[0][1] = "BITSH";
                   nearlocs[1][0] = Integer.toString(rst.getInt("Thumkunta"));
                   nearlocs[1][1] = "Thumkunta";
                   nearlocs[2][0] = Integer.toString(rst.getInt("Patny"));
                   nearlocs[2][1] = "Patny";
                   nearlocs[3][0] = Integer.toString(rst.getInt("Secunderabad"));
                   nearlocs[3][1] = "Secunderabad";
                   nearlocs[4][0] = Integer.toString(rst.getInt("Rasoolpura"));
                   nearlocs[4][1] = "Rasoolpura";
                }
            }
            
            rst.close();
            conn.close();
        }
        
        catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
        
        Arrays.sort(nearlocs, new Comparator<String[]>() {
	   	@Override
                //arguments to this method represent the arrays to be sorted   
		public int compare(String[] o1, String[] o2) {
                //get the item ids which are at index 0 of the array
	            Integer itemIdOne = Integer.parseInt(o1[0]);
	            Integer itemIdTwo = Integer.parseInt(o2[0]);
		    // sort on item id
		    return itemIdOne.compareTo(itemIdTwo);
		}
	});
        
        
        for(int i=0; i<3; i++)
        {
            try{
           
            String myUrl = "jdbc:mysql://localhost:3306/trial";
            Connection conn = DriverManager.getConnection(myUrl, "root", "242921326@pj");
            
            Statement st = conn.createStatement(); 
            rst = st.executeQuery("SELECT * FROM driver");
            
            while(rst.next())
            {
                if(nearlocs[i][1].equals(rst.getString("nearloc")) && Integer.parseInt(rst.getString("status")) == 0)
                {
                    this.dname = rst.getString("dname");
                    this.dphno = rst.getString("dphno");
                    this.car = rst.getString("car");
                    this.carid = rst.getString("carid");
                    this.rating = Float.toString(rst.getFloat("rating"));
                    c = 1;
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
            
            if(c==1)
                break;
        }
        
        if(c==1)
        {
            try{
           
            String myUrl = "jdbc:mysql://localhost:3306/trial";
            Connection conn = DriverManager.getConnection(myUrl, "root", "242921326@pj");
            
            Statement st = conn.createStatement();
            Statement st1 = conn.createStatement();
            rst = st.executeQuery("SELECT * FROM driver");
            
            while(rst.next())
            {
                if(this.carid.equals(rst.getString("carid")))
                {
                    st1.executeUpdate("UPDATE driver SET status = '1' where carid = '" + this.carid + "';");
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
    
    public void setnearloc(String to)
    {
        ResultSet rst = null;
        try{
           
            String myUrl = "jdbc:mysql://localhost:3306/trial";
            Connection conn = DriverManager.getConnection(myUrl, "root", "242921326@pj");
            
            Statement st = conn.createStatement(); 
            rst = st.executeQuery("SELECT * FROM driver");
            Statement st1 = conn.createStatement();
            while(rst.next())
            {
                if(rst.getString("carid").equals(this.carid))
                {
                    st1.executeUpdate("UPDATE driver SET nearloc ='" + to + "' WHERE carid = '" + this.carid + "';");
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
    
    public void setstatus(){
        
        ResultSet rst = null;
        int c=0;       
        try{
           
            String myUrl = "jdbc:mysql://localhost:3306/trial";
            Connection conn1 = DriverManager.getConnection(myUrl, "root", "242921326@pj");
            Connection conn = DriverManager.getConnection(myUrl, "root", "242921326@pj");
            Statement st1 = conn.createStatement();
            Statement st = conn1.createStatement(); 
            rst = st.executeQuery("SELECT * FROM driver");
            
            
            while(rst.next())
            {
                if(this.carid.equals(rst.getString("carid")))
                {
                    st1.executeUpdate("UPDATE driver SET status = '0' WHERE carid = '" + rst.getString("carid") +"';");
                    break;
                }
            }
            
            rst.close();
            conn.close();
            conn1.close();
        }
        
        catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
    }
}
