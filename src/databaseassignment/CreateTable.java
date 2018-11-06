package databaseassignment;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable {
    
    protected void buildTable() throws SQLException, IOException{
       
        String host = Login.getHost();
        String user = Login.getUser();
        String pass = Login.getPassword();
        String servicename = Login.getServiceName();
       
        try (             
            // Connects to the oracle driver
            Connection conn = DriverManager.getConnection
            ("jdbc:oracle:thin:"+user+"/"+pass+"@"+host+":1521:"+servicename);    
        )
        {       
            conn.setAutoCommit(false);
            Statement stmt = conn.createStatement(); 

            // Creates table using with two columns fnum and film. Film having a data type of XMLTYPE
            stmt.executeUpdate("CREATE TABLE ASS2_Film(Fnum SMALLINT CONSTRAINT Fnum_PK PRIMARY KEY, film SYS.XMLTYPE)");
            System.out.println("Table successfully created!!");

        }catch (Exception e) 
        {
            System.out.println(e);
        }

   }// finish createTable method
  
}//finish CreateTable class
