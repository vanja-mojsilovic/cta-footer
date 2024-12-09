package Spothopper.QA.TestCases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import org.testng.annotations.Test;


public class AwsDatabaseTest {
	private String url = "jdbc:postgresql://localhost:5432/spothopper_production";
	private String user = "vmojsilovic";
	private String password = "GLBUVeYyP9mjHzymcwFb";
	private Connection connection = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
	
	
	@Test
    public void testDatabaseQuery() {
        
        
        
        try {
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
            String sql = "SELECT * FROM spots limit 10";
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                System.out.println("id: "+resultSet.getString("id"));
                		
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
