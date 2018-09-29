package br.edu.cefsa.ftt.ec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.edu.cefsa.ftt.ec.model.Brand;
import br.edu.cefsa.ftt.util.DbUtil;
import br.edu.cefsa.ftt.util.MyException;

public class BrandDao {
	
	private Connection connection;
	
	public BrandDao() {
		connection = DbUtil.getConnection();
	}
	
	public void addBrand(Brand brand) {
        
    	try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO BRAND (NAME) VALUES (?)");
            
            // Parameters start with 1
            preparedStatement.setString(1, brand.getName());
            
            preparedStatement.executeUpdate();            

        } catch (SQLException e) {
            e.printStackTrace();
            
            throw new ArithmeticException("BrandDao: addBrand: " + e.getMessage()); 
        }
    } //addBrand
	
	public void deleteBrand(Brand brand) {
        try {
            
        	PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM BRAND WHERE ID=?");
            
            // Parameters start with 1
            preparedStatement.setLong(1, brand.getId());
            
            int updates = preparedStatement.executeUpdate();
            
            if(updates == 0) {
            	String message = "BRAND id " + brand.getId() + " is not found!";
            	
            	throw new MyException(message);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    } //deleteBrand

	public void updateBrand(Brand brand) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE BRAND SET NAME=? "
                                               + "WHERE ID=?");
            
            // Parameters start with 1
            preparedStatement.setString(1, brand.getName());
            preparedStatement.setLong(2, brand.getId());
            
            int updates = preparedStatement.executeUpdate();
            
            if(updates == 0) {
            	String message = "BRAND id " + brand.getId() + " is not found!";
            	
            	throw new MyException(message);
            }
            
            

        } catch (SQLException e) {
            e.printStackTrace();
        }
    } //updateBrand
	
	public List<Brand> getAllBrands() {
        
    	List<Brand> brands = new ArrayList<Brand>();
        
        try {
            Statement statement = connection.createStatement();
            
            ResultSet rs = statement.executeQuery("SELECT * FROM BRAND");
            
            boolean found = false;
            
            while (rs.next()) {
                found = true;
                
            	Brand b = new Brand();
                
            	b.setId(rs.getLong("ID"));
                b.setName(rs.getString("NAME"));

                brands.add(b);
            }
            
            if (!found) {
            	String message = "No BRAND data found!";
            	
            	throw new MyException(message);
			}
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return brands;
    } //getAllBrands
	
	public Brand getBrandById(Brand brand) {

		Brand b = new Brand();
        
    	try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("SELECT * FROM BRAND WHERE ID=?");
            
            preparedStatement.setLong(1, brand.getId());
            ResultSet rs = preparedStatement.executeQuery();

            if (!rs.next()) {
            	String message = "BRAND id " + brand.getId() + " not found!";
            	
            	throw new MyException(message);
            } 
            else {
                b.setId(rs.getLong("ID"));
                b.setName(rs.getString("NAME"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return b;
    } //getBrandById
}
