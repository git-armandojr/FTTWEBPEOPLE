package br.edu.cefsa.ftt.ec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import br.edu.cefsa.ftt.ec.model.People;
import br.edu.cefsa.ftt.util.DbUtil;
import br.edu.cefsa.ftt.util.MyException;

public class PeopleDao {

    private Connection connection;

    public PeopleDao() {
        connection = DbUtil.getConnection();
    } //PeopleDao

    public void addPeople(People people) {
        
    	try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO PEOPLE (NAME, EMAIL, DOB, COLOR, CARDTYPE, GENDER, PERIOD, VALUATION) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            
            // Parameters start with 1
            preparedStatement.setString(1, people.getName());
            preparedStatement.setString(2, people.getEmail());
            preparedStatement.setDate(3, new java.sql.Date(people.getDob().getTime())); //java.sql.Date não tem time zone...
            preparedStatement.setString(4, people.getColor());
            preparedStatement.setString(5, people.getCardType());
            preparedStatement.setString(6, people.getGender());
            preparedStatement.setString(7, people.getPeriod());
            preparedStatement.setFloat(8, people.getValuation());
            
            preparedStatement.executeUpdate();
            

        } catch (SQLException e) {
            e.printStackTrace();
            
            throw new ArithmeticException("PeopleDao: addPeople: " + e.getMessage()); 
        }
    } //addPeople

    public void deletePeople(People people) {
        try {
            
        	PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM PEOPLE WHERE ID=?");
            
            // Parameters start with 1
            preparedStatement.setLong(1, people.getId());

            int updates = preparedStatement.executeUpdate();
            
            if(updates == 0) {
            	String message = "PEOPLE id " + people.getId() + " is not found!";
            	
            	throw new MyException(message);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    } //deletePeople

    public void updatePeople(People people) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE PEOPLE SET NAME=?, " 
                    		                          + "EMAIL=?, " 
                    		                          + "DOB=?, " 
                    		                          + "COLOR=?, " 
                    		                          + "CARDTYPE=?, " 
                    		                          + "GENDER=?, " 
                    		                          + "PERIOD=?, " 
                    		                          + "VALUATION=? " 
                                               + "WHERE ID=?");
            
            // Parameters start with 1
            preparedStatement.setString(1, people.getName());
            preparedStatement.setString(2, people.getEmail());
            preparedStatement.setDate(3, new java.sql.Date(people.getDob().getTime()));
            preparedStatement.setString(4, people.getColor());
            preparedStatement.setString(5, people.getCardType());
            preparedStatement.setString(6, people.getGender());
            preparedStatement.setString(7, people.getPeriod());
            preparedStatement.setFloat(8, people.getValuation());            
            preparedStatement.setLong(9, people.getId());
            
            int updates = preparedStatement.executeUpdate();
            
            if(updates == 0) {
            	String message = "PEOPLE id " + people.getId() + " is not found!";
            	
            	throw new MyException(message);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    } //updatePeople

    public List<People> getAllPeoples() {
        
    	List<People> peoples = new ArrayList<People>();
        
        try {
            Statement statement = connection.createStatement();
            
            ResultSet rs = statement.executeQuery("SELECT * FROM PEOPLE");
            
            boolean found = false;
            
            while (rs.next()) {
            	
            	found = true;
                
            	People p = new People();
                
            	p.setId(rs.getLong("ID"));
                p.setName(rs.getString("NAME"));
                p.setEmail(rs.getString("EMAIL"));
                p.setDob(rs.getDate("DOB"));
                p.setColor(rs.getString("COLOR"));
                p.setCardType(rs.getString("CARDTYPE"));
                p.setGender(rs.getString("GENDER"));
                p.setPeriod(rs.getString("PERIOD"));
                p.setValuation(rs.getFloat("VALUATION"));

                peoples.add(p);
            }
            
            if (!found) {
            	String message = "No PEOPLE data found!";
            	
            	throw new MyException(message);
			}
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return peoples;
    } //getAllPeoples

    public People getPeopleById(People people) {

    	People p = new People();
        
    	try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("SELECT * FROM PEOPLE WHERE ID=?");
            
            preparedStatement.setLong(1, people.getId());
            ResultSet rs = preparedStatement.executeQuery();
           
            if (!rs.next()) {
            	String message = "PEOPLE id " + people.getId() + " not found!";
            	
            	throw new MyException(message);
            }            
            else {
                p.setId(rs.getLong("ID"));
                p.setName(rs.getString("NAME"));
                p.setEmail(rs.getString("EMAIL"));
                p.setDob(rs.getDate("DOB"));
                p.setColor(rs.getString("COLOR"));
                p.setColor(rs.getString("CARDTYPE"));
                p.setGender(rs.getString("GENDER"));
                p.setColor(rs.getString("PERIOD"));
                p.setValuation(rs.getFloat("VALUATION"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return p;
    } //getPeopleById
    
} //PeopleDao