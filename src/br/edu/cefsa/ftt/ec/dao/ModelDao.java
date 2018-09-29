package br.edu.cefsa.ftt.ec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.edu.cefsa.ftt.ec.model.Model;
import br.edu.cefsa.ftt.util.DbUtil;
import br.edu.cefsa.ftt.util.MyException;

public class ModelDao {
	
	private Connection connection;
	
	public ModelDao() {
		connection = DbUtil.getConnection();
	} // ModelDao
	
	public void addModel(Model model) {
        
    	try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO MODEL (NAME, BRAND) VALUES (?, ?)");
            
            // Parameters start with 1
            preparedStatement.setString(1, model.getName());
            preparedStatement.setString(2, model.getBrand());
            
            preparedStatement.executeUpdate();            

        } catch (SQLException e) {
            e.printStackTrace();
            
            throw new ArithmeticException("ModelDao: addModel: " + e.getMessage()); 
        }
    } //addModel
	
	public void deleteModel(Model model) {
        try {
            
        	PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM MODEL WHERE ID=?");
            
            // Parameters start with 1
            preparedStatement.setLong(1, model.getId());
           
            int updates = preparedStatement.executeUpdate();
            
            if(updates == 0) {
            	String message = "MODEL id " + model.getId() + " is not found!";
            	
            	throw new MyException(message);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    } //deleteModel
	
	public void updateModel(Model model) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE MODEL SET NAME=?, "
                    		                         + "BRAND=? " 
                                               + "WHERE ID=?");
            
            // Parameters start with 1
            preparedStatement.setString(1, model.getName());
            preparedStatement.setString(2, model.getBrand());
            preparedStatement.setLong(3, model.getId());
            
            int updates = preparedStatement.executeUpdate();
            
            if(updates == 0) {
            	String message = "MODEL id " + model.getId() + " is not found!";
            	
            	throw new MyException(message);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    } //updateModel
	
	public List<Model> getAllModels() {
        
    	List<Model> models = new ArrayList<Model>();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM MODEL");
            
            boolean found = false;
            
            while (rs.next()) {
            	found = true;
            	Model m = new Model();
                
            	m.setId(rs.getLong("ID"));
                m.setName(rs.getString("NAME"));
                m.setBrand(rs.getString("BRAND"));

                models.add(m);
            }
            
            if (!found) {
            	String message = "No MODEL data found!";
            	
            	throw new MyException(message);
			}
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return models;
    } //getAllModels
	
	public Model getModelById(Model model) {

		Model m = new Model();
        
    	try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("SELECT * FROM MODEL WHERE ID=?");
            
            preparedStatement.setLong(1, model.getId());
            ResultSet rs = preparedStatement.executeQuery();

            if (!rs.next()) {
            	String message = "MODEL id " + model.getId() + " not found!";
            	
            	throw new MyException(message);
            } 
            else {
                m.setId(rs.getLong("ID"));
                m.setName(rs.getString("NAME"));
                m.setBrand(rs.getString("BRAND"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return m;
    } //getModelById
}
