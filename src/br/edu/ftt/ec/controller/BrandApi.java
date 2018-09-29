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

import br.edu.cefsa.ftt.ec.dao.BrandDao;
import br.edu.cefsa.ftt.ec.model.Brand;

/**
 * Servlet implementation class Brand
 */
@WebServlet("/brand")
public class BrandApi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BrandApi() {
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
				Brand brand = new Brand();
				
				brand.setId(pid);
				
				BrandDao bd = new BrandDao();
				
				brand = bd.getBrandById(brand);
				
				json = gson.toJson(brand);
			}
			else {
				List<Brand> models = new ArrayList<Brand>();
				
				BrandDao bd = new BrandDao();
				
				models = bd.getAllBrands();
				
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
		Brand b = new Brand(
				request.getParameter("pid"), //ID sera gerado no BD pela sequence
				request.getParameter("pname"));
		
		System.out.print(b);
		
		BrandDao bd = new BrandDao();
		
		String status = null;
		String message = null;
		String now = String.valueOf(new Date());
		
		try {
		   bd.addBrand(b);
		   
		   status = "OK";
		   message = "BRAND data saved!";
		   
		} catch (Exception e) {
			status = "Error";
			message = e.getMessage();
			System.err.println(now +  " - Ops!! - " + message);
			System.err.println(now +  " - Ops!! - " + b);
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
		Brand brand = new Brand(
				request.getParameter("pid"), //ID sera gerado no BD pela sequence
				request.getParameter("pname"));
		
		System.out.print(brand);
		
		BrandDao bd = new BrandDao();
		
		String status = null;
		String message = null;
		String now = String.valueOf(new Date());
		
		try {
		   bd.updateBrand(brand);
		   
		   status = "OK";
		   message = "BRAND data updated!";
		} catch (Exception e) {
			status = "Error";
			message = e.getMessage();
			System.err.println(now +  " - Ops!! - " + message);
			System.err.println(now +  " - Ops!! - " + brand);
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
		Brand brand = new Brand();
		
		brand.setId(request.getParameter("pid"));
		
		BrandDao bd = new BrandDao();
		
		//brand = bd.getBrandById(brand);
		
		//System.out.print(brand);
		
		String status = null;
		String message = null;
		String now = String.valueOf(new Date());
		
		try {
		   bd.deleteBrand(brand);
		   
		   status = "OK";
		   message = "BRAND data deleted!";
		   now = String.valueOf(new Date());
		   
		} catch (Exception e) {
			status = "Error";
			message = e.getMessage();
			System.err.println(now +  " - Ops!! - " + message);
			System.err.println(now +  " - Ops!! - " + brand);
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
