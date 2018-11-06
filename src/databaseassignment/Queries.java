package databaseassignment;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Queries {
    
    protected void query()throws SQLException, IOException{
       
        String host = Login.getHost();
        String user = Login.getUser();
        String pass = Login.getPassword();
        String servicename = Login.getServiceName();
        
        try (                                  
        Connection conn = DriverManager.getConnection
        ("jdbc:oracle:thin:"+user+"/"+pass+"@"+host+":1521:"+servicename);
        Statement queryStatement = conn.createStatement();      
        )
        {
            conn.setAutoCommit(false);

            // Query1 - extract film titles from ASS2_Film table and returning to string by.getStringVal(). 
            ResultSet outcome = queryStatement.executeQuery("SELECT a.film.extract('/film/title').getStringVal() FROM ASS2_Film a");
            System.out.println("\nQuery 1 - Title of all films: ");

                //using while loop to print out results
                while(outcome.next())
                    System.out.println(outcome.getString(1) );   


            // Query 2 - Get all film titles without <title> tag by using text(). This is achieved from the "/text()". 
            outcome = queryStatement.executeQuery("SELECT a.film.extract('/film/title/text()').getStringVal() FROM ASS2_Film a");
            System.out.println("Query 2 - Film Titles without surrounding <title> tag: ");

                while(outcome.next())
                    System.out.println(outcome.getString(1) );     

            /*Query 3 - Using a predicate ([]) to achive actors within associated film title (Completed by: /cast/performer/actor). 
              Now that we have retrieved the actors, we have to provide an argument by inserting a where clause, so that we can 
              retrieve all actors WHERE the film title equals "Godfather, The". Lastly existsnode returns 0 or 1 by taking this
              XPATH expression as an argument
              The existsNode method takes an XPATH expression as an arguments and returns 0 or 1.   */
            outcome = queryStatement.executeQuery("SELECT a.film.extract('/film[title=\"Godfather, The\"]/cast/performer/actor').getStringVal() "
            + "FROM ASS2_Film a WHERE a.film.existsnode('/film[title=\"Godfather, The\"]')=1");
            System.out.println("\nQuery 3 - Names of all actors who appeard in the Godfather: ");

                while(outcome.next())
                    System.out.println(outcome.getString(1));


            /*Query #4 - Selecting both the title and year by the pipe (|) and arguing this value WHERE film genre is crime. 
              Single slash (/) used to find the root of the node. 
              Double slash (//) used to get the descendants. 
            */
            outcome = queryStatement.executeQuery("SELECT a.film.extract('/film[genres/genre=\"Crime\"]/title|//year').getStringVal()"
            + "FROM ASS2_Film a WHERE a.film.existsnode('/film[genres/genre=\"Crime\"]')=1");
            System.out.println("Query 4 - The title and year of all crime films: ");

                while(outcome.next())
                    System.out.println(outcome.getString(1));


            /*Query 5 - Getting the title and year with specified actor. Within select statement, getting the year and title of which the actor was in. Then providing an
              argument to print out the year and title WHERE of that actor. */ 
            outcome = queryStatement.executeQuery("SELECT a.film.extract('/film[cast/performer/actor=\"Marlon Brando\"]/year|//title').getStringVal() "
                    + "FROM ASS2_Film a \n" +
            "WHERE a.film.existsnode('/film[cast/performer/actor=\"Marlon Brando\"]')=1");
            System.out.println("\nQuery 5 - The title and year of all films in which Marlon Brando has acted: ");

                while(outcome.next())
                    System.out.println(outcome.getString(1));


            /*Query 6 - Providing a predicate function to count movies with one director. Therefore its counting to =1. Using double slash to get children 
               of director node (//director[1]). The '[1]' is used to achieve the one director. Then using the select statment to collect the title.   */
            outcome = queryStatement.executeQuery("SELECT a.film.extract('/film/title').getStringVal() \n" +
            "FROM ASS2_Film a \n" +
            "WHERE a.film.existsnode('/film[count(//director) =1]//director[1]')=1");
            System.out.println("\nQuery 6 - Movies with one director: ");

                while(outcome.next())
                    System.out.println(outcome.getString(1));


            /*Query 7 - Within the WHERE clause, it has counted that it wants more than one director. Therefore we are getting the film title and directors   
              where they have directed more than one film. */
            outcome = queryStatement.executeQuery("SELECT a.film.extract('/film/title | /film/directors/director' ).getStringVal() FROM ASS2_Film a "
                    + "WHERE a.film.existsnode('/film[count(//director) >1]//director[1]')=1");
            System.out.println("Query 7 - Title and names of directors of all films which have more than one director: ");

                while(outcome.next())
                    System.out.println(outcome.getString(1));


            /*Query 8 - Firstly, we are a selecting the names of the directors.  Within the WHERE CLAUSE it is then verifying if a second director is within
              the xml file. If not continue to the AND statment. Therefore within the OR clause, it verifies whether the actor is equal to the director.*/
            outcome = queryStatement.executeQuery("SELECT " +
            "a.Film.extract('/film//director/text()').getStringVal()" +
            "FROM ASS2_FILM a " +
            "WHERE a.film.existsNode('/film//director[2]')=0" +
            "AND (a.film.extract('/film//director/text()').getStringVal() = a.film.extract('/film/cast/performer[1]/actor/text()').getStringVal() " +
            "OR a.film.extract('/film//director/text()').getStringVal() = a.film.extract('/film/cast/performer[2]/actor/text()').getStringVal()" + 
            "OR a.film.extract('/film//director/text()').getStringVal() = a.film.extract('/film/cast/performer[3]/actor/text()').getStringVal()" + 
            "OR a.film.extract('/film//director/text()').getStringVal() = a.film.extract('/film/cast/performer[4]/actor/text()').getStringVal()" +
            "OR a.film.extract('/film//director/text()').getStringVal() = a.film.extract('/film/cast/performer[5]/actor/text()').getStringVal())" );

            System.out.println("Query 8 - The name of all actors who have acted in a film and been sole director of the film: "); 

                while(outcome.next())
                {
                System.out.println(outcome.getString(1));
                }

        }catch (SQLException exc) 
        {
            System.out.println(exc);
        }  
    
  }//end method query
    
}//end class Queries
