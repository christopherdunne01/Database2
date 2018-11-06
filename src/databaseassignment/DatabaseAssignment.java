package databaseassignment;

import java.io.IOException;
import java.sql.SQLException;

// Christopher Dunne - B00684533

public class DatabaseAssignment {
         
    String host = Login.getHost();
    String user = Login.getUser();
    String pass = Login.getPassword();
    String servicename = Login.getServiceName();
    
    public static void main(String[] args) throws SQLException, IOException{
  
        try
        {
          Class.forName ("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) 
        {
            System.out.println ("Could not load the driver" +e); 
        }
    
        Login log = new Login(); 
        CreateTable create = new CreateTable();
        InsertData insert = new InsertData(); 
        Queries queryData = new Queries(); 

        log.filmLogin(); // Logs into the database
        create.buildTable(); // creates the table with xml type
        insert.insertData(); // inserts the data into the xml type
        queryData.query(); // queries the data

    
    }// end main method

}// end Database Assigment 

