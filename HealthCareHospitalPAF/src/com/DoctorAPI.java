package com;
	import com.Doctor;
	import java.io.IOException;
	import javax.servlet.ServletException;
	import javax.servlet.annotation.WebServlet;
	import javax.servlet.http.HttpServlet;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;
	import java.io.IOException;
	import java.util.HashMap;
	import java.util.Map;
	import java.util.Scanner;
	/**
	 * Servlet implementation class paymentAPI
	 */
	@WebServlet("/DoctorAPI")
	public class  DoctorAPI extends HttpServlet {
		private static final long serialVersionUID = 1L;
		
		Doctor DocObj = new Doctor();
	       
	    /**
	     * @see HttpServlet#HttpServlet()
	     */
	    public DoctorAPI() {
	        super();
	        // TODO Auto-generated constructor stub
	    }

	    
	    
	    
	    @SuppressWarnings("rawtypes")
		private static Map getParasMap(HttpServletRequest request)
	    {
	    Map<String, String> map = new HashMap<String, String>();
	    try
	    {
	    Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
	    String queryString = scanner.hasNext() ?
	    scanner.useDelimiter("\\A").next() : "";
	    scanner.close();
	    String[] params = queryString.split("&");
	    for (String param : params)
	    {
	    
	    String[] p = param.split("=");
	    map.put(p[0], p[1]);
	    }
	    }
	    catch (Exception e)
	    {
	    }
	    return map;
	    }
	    
	    
	    
	    
		/**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			response.getWriter().append("Served at: ").append(request.getContextPath());
		}

		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException
				{
			
				String output = DocObj.insertDoctor(
				request.getParameter("doctorid"),
				request.getParameter("name"),
				request.getParameter("address"),
				request.getParameter("gender"),
				request.getParameter("specialization"),
				request.getParameter("registHospital"),
				request.getParameter("email"),
				request.getParameter("phone"));
				response.getWriter().write(output);
				
				}
		
		
		/**
		 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
		 */
		protected void doPut(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException
				{
				Map paras = getParasMap(request);
				String output = DocObj.updateDoctor(paras.get("DoctorIDSave").toString(),
				paras.get("name").toString(),
				paras.get("address").toString(),
				paras.get("gender").toString(),
				paras.get("specialization").toString(),
				paras.get("registHospital").toString(),
				paras.get("email").toString(),
				paras.get("phone").toString());
				response.getWriter().write(output);
						
				}
			
		/**
		 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
		 */
		protected void doDelete(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException
				{
				Map paras = getParasMap(request);
				String output = DocObj.deleteDoctor(paras.get("DoctorID").toString());
				response.getWriter().write(output);
				}

	}



