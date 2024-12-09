package Spothopper.QA.TestCases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import org.testng.annotations.Test;


public class DatabaseTest {
	private String url = "jdbc:mysql://localhost:3306/mydatabase";
	private String user = "root";
	private String password = "";
	private Connection connection = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
	
	
	@Test
    public void testDatabaseQuery() {
        
        
        
        try {
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
            String sql = "SELECT * FROM entries";
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                System.out.println("id: "+resultSet.getString("id")
                		+" first_name: "+resultSet.getString("first_name")
                		+" last_name: "+resultSet.getString("last_name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
	
	@Test
    public void insertIntoDatabaseQuery() {
              
        
        try {
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
            String sql = "INSERT INTO entries (text_value,first_name, last_name) VALUES ('sample text 1','John', 'Doe')";
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                System.out.println("id: "+resultSet.getString("id")
                		+" first_name: "+resultSet.getString("first_name")
                		+" last_name: "+resultSet.getString("last_name")
                	  	+" text_value: "+resultSet.getString("text_value"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
