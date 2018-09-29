package br.edu.cefsa.ftt.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {

    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection != null)
            return connection;
        else {
            try {

                String driver = "com.mysql.cj.jdbc.Driver";
                String url = "jdbc:mysql://localhost:3306/ftt?useTimezone=true&serverTimezone=UTC&useSSL=false";
                String user = "scott";
                String password = "tiger";
            	
                //DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            	Class.forName(driver); //verifica se o driver do bd está no class path...
                
                connection = DriverManager.getConnection(url, user, password);
                
                connection.setAutoCommit(true);
           
            
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            /*} catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();*/
            }
            return connection;
        }

    }
}