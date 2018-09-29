package br.edu.ftt.ec.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import br.edu.cefsa.ftt.ec.dao.PeopleDao;
import br.edu.cefsa.ftt.ec.model.People;

/**
 * Servlet implementation class People
 */
@WebServlet("/people")
public class PeopleApi extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//private ArrayList<People> peopleData = new ArrayList<People>(); //Pode remover...
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PeopleApi() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*
		response.getWriter()
                    .append(request.getParameter("pid")).append(";")
		            .append(request.getParameter("pname")).append(";")
		            .append(request.getParameter("email")).append(";")
		            .append(request.getParameter("dob")).append(";")
		            .append(request.getParameter("color")).append(";")
		            .append(request.getParameter("cardType")).append(";")
		            .append(request.getParameter("value")).append(";")
		            .append(request.getParameter("periodM")).append(";")
		            .append(request.getParameter("periodA")).append(";")
		            .append(request.getParameter("periodN"));
		
		System.out.println(request);
		*/
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
	    
	    String json = null;
		String pid = request.getParameter("pid");
		
		
		try {
			if (pid != null) {
				People people = new People();
				
				people.setId(pid);
				
				PeopleDao pd = new PeopleDao();
				
				people = pd.getPeopleById(people);
				
				json = gson.toJson(people);
			}
			else {
				List<People> peoples = new ArrayList<People>();
				
				PeopleDao pd = new PeopleDao();
				
				peoples = pd.getAllPeoples();
				
				json = gson.toJson(peoples);
			}			
			
		} catch (Exception e) {			
			String status = "Error";
			String message = e.getMessage();
			String now = String.valueOf(new Date());
			
			System.err.println(status + ": " + now +  " - Ops!! - " + message);
			e.printStackTrace();
			
			JsonObject jsonObject = new JsonObject();
			
			jsonObject.addProperty("Status", status);
			jsonObject.addProperty("Message", message);
			jsonObject.addProperty("Time", now);
			
			json = gson.toJson(jsonObject);
		}
		
		response.setContentType("application/json"); //MIME Type
		response.setCharacterEncoding("utf-8");

		response.getWriter().print(json);
        response.flushBuffer();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		People p = new People(
				request.getParameter("pid"), //ID sera gerado no BD pela sequence
				request.getParameter("pname"),
				request.getParameter("email"),
        		request.getParameter("dob"),
        		request.getParameter("valuation"),
        		request.getParameter("color"),
        		request.getParameter("cardType"),
        		request.getParameter("gender"),
        		request.getParameter("periodM") + ";" +
        		request.getParameter("periodA") + ";" +
        		request.getParameter("periodN"));
		
		System.out.print(p);
		
		PeopleDao pd = new PeopleDao();
		
		String status = null;
		String message = null;
		String now = String.valueOf(new Date());
		
		try {
		   pd.addPeople(p);
		   
		   status = "OK";
		   message = "PEOPLE data saved!";
		   
		} catch (Exception e) {
			status = "Error";
			message = e.getMessage();
			System.err.println(now +  " - Ops!! - " + message);
			System.err.println(now +  " - Ops!! - " + p);
			e.printStackTrace();
		}
		
		response.setContentType("application/json"); //MIME Type
		response.setCharacterEncoding("utf-8");
		
	    //create Json Object
		JsonObject json = new JsonObject();

		// put some value pairs into the JSON object .
		
		json.addProperty("Status", status);
		json.addProperty("Message", message);
		json.addProperty("Time", now);


		response.getWriter().print(json.toString());
        response.flushBuffer();
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		People people = new People(
				request.getParameter("pid"), //ID sera gerado no BD pela sequence
				request.getParameter("pname"),
				request.getParameter("email"),
        		request.getParameter("dob"),
        		request.getParameter("valuation"),
        		request.getParameter("color"),
        		request.getParameter("cardType"),
        		request.getParameter("gender"),
        		request.getParameter("periodM") + ";" +
        		request.getParameter("periodA") + ";" +
        		request.getParameter("periodN"));
		
		System.out.print(people);
		
		PeopleDao pd = new PeopleDao();
		
		String status = null;
		String message = null;
		String now = String.valueOf(new Date());
		
		try {
		   pd.updatePeople(people);
		   
		   status = "OK";
		   message = "PEOPLE data updated!";
		   
		} catch (Exception e) {
			status = "Error";
			message = e.getMessage();
			System.err.println(now +  " - Ops!! - " + message);
			System.err.println(now +  " - Ops!! - " + people);
			e.printStackTrace();
		}
		
		response.setContentType("application/json"); //MIME Type
		response.setCharacterEncoding("utf-8");
		
	    //create Json Object
		JsonObject json = new JsonObject();

		// put some value pairs into the JSON object .
		
		json.addProperty("Status", status);
		json.addProperty("Message", message);
		json.addProperty("Time", now);


		response.getWriter().print(json.toString());
        response.flushBuffer();
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		People people = new People();
		
		people.setId(request.getParameter("pid"));
		
		PeopleDao pd = new PeopleDao();
		
		//people = pd.getPeopleById(people);
		
		System.out.print(people);
		
		String status = null;
		String message = null;
		String now = String.valueOf(new Date());		
		
		try {
		   pd.deletePeople(people);
		   
		   status = "OK";
		   message = "PEOPLE data deleted!";
		   
		} catch (Exception e) {
			status = "Error";
			message = e.getMessage();
			System.err.println(now +  " - Ops!! - " + message);
			System.err.println(now +  " - Ops!! - " + people);
			e.printStackTrace();
		}
		
		response.setContentType("application/json"); //MIME Type
		response.setCharacterEncoding("utf-8");
		
	    //create Json Object
		JsonObject json = new JsonObject();

		// put some value pairs into the JSON object .
		
		json.addProperty("Status", status);
		json.addProperty("Message", message);
		json.addProperty("Time", now);

		response.getWriter().print(json.toString());
        response.flushBuffer();
	}

}
