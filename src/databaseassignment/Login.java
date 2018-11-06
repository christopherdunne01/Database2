package databaseassignment;

import java.sql.SQLException;
import java.util.Scanner;

public class Login {
  
    private String user, host, password; 
    
    private static String filmUsername = "";
    private static String filmHost = "";
    private static String filmPassword = "";
    private static String filmServiceName = "";    
            
        protected void filmLogin() throws SQLException 
        {
            try {
            Class.forName ("oracle.jdbc.driver.OracleDriver");
            } catch (ClassNotFoundException e) 
            {
              System.out.println ("Could not load the driver"); 
            }
              // use a scanner to scan through all inputs. 
              Scanner sc = new Scanner(System.in);
              
              System.out.println("Type Username: ");
                user = sc.next();
                System.out.println("Type password: ");
                password = sc.next();
                System.out.println("Type hostname or ipaddress: ");
                host = sc.next();

                filmUsername = user;
                filmPassword = password;
                filmHost = host;
                filmServiceName = "orcl"; 

          System.out.println("\nAccess Granted!"); 
        }// end filmLogin
       
    
    /*Using getters to pass these variables through other classes
       - ensuring no hard code is written. */
    public static String getUser()
    {
        return filmUsername;
    }
    
    public static String getPassword()
    {
        return filmPassword;
    }
    
    public static String getHost()
    {
        return filmHost; 
    }
    
    public static String getServiceName()
    {
        return filmServiceName;
    }
  
}// end class Login
    



