package databaseassignment;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import oracle.jdbc.OraclePreparedStatement;
import java.io.IOException;
import oracle.xdb.XMLType;

public class InsertData  {
    
    protected void insertData() throws SQLException, IOException{
       
        // Collects the login data from the getter in class Login.
        String host = Login.getHost();
        String user = Login.getUser();
        String pass = Login.getPassword();
        String servicename = Login.getServiceName();
  
       try( 
            Connection conn = DriverManager.getConnection
            ("jdbc:oracle:thin:"+user+"/"+pass+"@"+host+":1521:"+servicename);      
          )
        {
            /*Once a connection is open, it is on auto-commit mode. Therefore you have to set the connection
              to false. Allowing a varity of statements on the one transaction.*/
            conn.setAutoCommit(false); 

            /*InsertData1.xml - Read this data from FileInputStream. Connect to server via 'conn' with fileOne.
              Insert the data using the SQL statement. The '(?,?)' is used to firstly insert the default fnum '1',
              and the xml data type into the second column. */
            FileInputStream fileOne = new FileInputStream("xmltypedata/InsertData1.xml");
            XMLType xmlv1 = new XMLType(conn, fileOne);
            String sqltxt = "INSERT INTO ASS2_Film VALUES(?, ?)";
            
            /*Connect the sql text to the oracle server. The first '1' within the 'setInt(1,1)' means to set  
              the parameter index to a value of one. While the second '1' within the method is to place it in 
              the first table. Therefore you will see the second paramter increment each time, to insert it into
              each file. */
            OraclePreparedStatement oracleStmt = (OraclePreparedStatement) conn.prepareStatement(sqltxt);
            oracleStmt.setInt(1, 1);

            /* The second paramter (xmlv1- which is an object/file) has been put in the second column.*/
            oracleStmt.setObject(2, xmlv1);
            oracleStmt.execute();

            
            //InsertData2.xml
            FileInputStream fileTwo = new FileInputStream("xmltypedata/InsertData2.xml");
            XMLType xmlv2 = new XMLType(conn, fileTwo);
            sqltxt = "INSERT INTO ASS2_Film VALUES(?, ?)";
            
            oracleStmt = (OraclePreparedStatement) conn.prepareStatement(sqltxt);
            oracleStmt.setInt(1, 2);

            oracleStmt.setObject(2, xmlv2);
            oracleStmt.execute();

            
            // InsertData3.xml
            FileInputStream fileThree = new FileInputStream("xmltypedata/InsertData3.xml");
            XMLType xmlv3 = new XMLType(conn, fileThree);
            sqltxt = "INSERT INTO ASS2_Film VALUES(?, ?)";
           
            oracleStmt = (OraclePreparedStatement) conn.prepareStatement(sqltxt);
            oracleStmt.setInt(1, 3);

            oracleStmt.setObject(2, xmlv3);
            oracleStmt.execute();

            
            // InsertData4.xml
            FileInputStream fileFour = new FileInputStream("xmltypedata/InsertData4.xml");
            XMLType xmlv4 = new XMLType(conn, fileFour);
            sqltxt = "INSERT INTO ASS2_Film VALUES(?, ?)";
           
            oracleStmt = (OraclePreparedStatement) conn.prepareStatement(sqltxt);
            oracleStmt.setInt(1, 4);

            oracleStmt.setObject(2, xmlv4);
            oracleStmt.execute();

            
            // InsertData5.xml
            FileInputStream fileFive = new FileInputStream("xmltypedata/InsertData5.xml");
            XMLType xmlv5 = new XMLType(conn, fileFive);
            sqltxt = "INSERT INTO ASS2_Film VALUES(?, ?)";
            
            oracleStmt = (OraclePreparedStatement) conn.prepareStatement(sqltxt);
            oracleStmt.setInt(1, 5);

            oracleStmt.setObject(2, xmlv5);
            oracleStmt.execute();

            System.out.println("Data Inserted!!!");

        }catch (Exception e) {
            System.out.println(e);
        }// end try-catch

   }// end method insertTables

}// end class InsertData
