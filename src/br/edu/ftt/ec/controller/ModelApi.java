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

import br.edu.cefsa.ftt.ec.dao.ModelDao;
import br.edu.cefsa.ftt.ec.model.Model;

/**
 * Servlet implementation class Model
 */
@WebServlet("/model")
public class ModelApi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModelApi() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
	    
	    String json = null;
		String pid = request.getParameter("pid");		
		
		try {
			if (pid != null) {
				Model model = new Model();
				
				model.setId(pid);
				
				ModelDao md = new ModelDao();
				
				model = md.getModelById(model);
				
				json = gson.toJson(model);
			}
			else {
				List<Model> models = new ArrayList<Model>();
				
				ModelDao md = new ModelDao();
				
				models = md.getAllModels();
				
				json = gson.toJson(models);
			}			
		} catch (Exception e) {
			String status = null;
			String message = null;
			String now = null;
			
			status = "Error";
			message = e.getMessage();
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
		Model m = new Model(
				request.getParameter("pid"), //ID sera gerado no BD pela sequence
				request.getParameter("pname"),
				request.getParameter("brand"));
		
		System.out.print(m);
		
		ModelDao md = new ModelDao();
		
		String status = null;
		String message = null;
		String now = String.valueOf(new Date());
		
		try {
		   md.addModel(m);
		   
		   status = "OK";
		   message = "MODEL data saved!";
		   
		} catch (Exception e) {
			status = "Error";
			message = e.getMessage();
			System.err.println(now +  " - Ops!! - " + message);
			System.err.println(now +  " - Ops!! - " + m);
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
		Model model = new Model(
				request.getParameter("pid"), //ID sera gerado no BD pela sequence
				request.getParameter("pname"),
        		request.getParameter("brand"));
		
		System.out.print(model);
		
		ModelDao md = new ModelDao();
		
		String status = null;
		String message = null;
		String now = String.valueOf(new Date());
		
		try {
		   md.updateModel(model);
		   
		   status = "OK";
		   message = "MODEL data updated!";
		   
		} catch (Exception e) {
			status = "Error";
			message = e.getMessage();
			System.err.println(now +  " - Ops!! - " + message);
			System.err.println(now +  " - Ops!! - " + model);
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
		Model model = new Model();
		
		model.setId(request.getParameter("pid"));
		
		ModelDao md = new ModelDao();
		
		model = md.getModelById(model);
		
		System.out.print(model);
		
		String status = null;
		String message = null;
		String now = String.valueOf(new Date());
		
		try {
		   md.deleteModel(model);
		   
		   status = "OK";
		   message = "MODEL data deleted!";
		   
		} catch (Exception e) {
			status = "Error";
			message = e.getMessage();
			System.err.println(now +  " - Ops!! - " + message);
			System.err.println(now +  " - Ops!! - " + model);
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
