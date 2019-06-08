import java.sql.*;
public class Connectionis {
	  public Connection connect() throws Exception{
          Class.forName("com.mysql.jdbc.Driver");
    	  Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","");
		  return con;
		}
    }


