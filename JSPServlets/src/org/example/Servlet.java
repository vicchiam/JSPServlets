package org.example;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.example.model.Count;
import org.example.model.beans.Administrator;



/**
 * Servlet implementation class Servlet
 */
public class Servlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final Logger log = LogManager.getLogger("Servlet: ");
	
	private DataSource ds;
	private Connection con;
	
	private String rutaJSP;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet() {
        super();
    }
    
    @Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		this.rutaJSP=config.getInitParameter("rutaJSP");
		
		//Log4j
		BasicConfigurator.configure();
		
		try{
			//BBDD
			InitialContext initContext=new InitialContext();
			Context env=(Context) initContext.lookup("java:comp/env");
			ds=(DataSource) env.lookup("jdbc/JSPServlets");
		}
		catch(NamingException e){
			log.error("Configure JDNI: "+e.getMessage());
		}
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session=request.getSession();
		
		String action=request.getParameter("action");
		
		try{
			con=ds.getConnection();
		}
		catch(SQLException e){
			log.error("Error to create connection: "+e.getMessage());
		}
		
		if(action!=null){
			
			if(action.equals("index")){
				this.setResponseController("index").forward(request, response);				
			}
			else if(action.equals("login")){
				this.setResponseController("login").forward(request, response);
			}
			else if(action.equals("logout")){
				session.invalidate();
				log.info("Session destroyed");
				this.setResponseController("login").forward(request, response);
			}
			else if(action.equals("listAdministrators")){
				List<Administrator> admins=new Count(con).listAdministrators();
				request.setAttribute("administrators", admins);
				this.setResponseController("listAdministrators").forward(request, response);
			}
			else if(action.equals("registerQuestion")){
				this.setResponseController(action).forward(request, response);
			}
			
		}
		else{
			this.setResponseController("login").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session=request.getSession();
		
		String action = request.getParameter("action");
				
		try{
			con=ds.getConnection();
		}
		catch(SQLException e){
			log.error("Error to create connection: "+e.getMessage());
		}
		
		if (action != null) {
			
			if (action.equals("startSession")) {
				
				String user = request.getParameter("user");
				String password = request.getParameter("password");
				
				Count count=new Count(con);
				
				if(count.login(user, password)){
					log.info("Login with user: "+user);
					session.setAttribute("mail", user);
					this.setResponseController("index").forward(request, response);
					
					if(request.getParameter("ckbox")!=null && request.getParameter("ckbox").equals("on")){
						Cookie cookie=new Cookie("user",user);
						cookie.setMaxAge(60*60*24);
						response.addCookie(cookie);
						log.info("Cookie created");
					}					
				}
				else{
					log.error("Login error user/password incorrect");
					request.setAttribute("error", "User/password incorrect");
					this.setResponseController("login").forward(request, response);
				}
				
			}			
			
		}
		else {
			this.setResponseController("login").forward(request, response);
		}
		
		try{
			con.close();
		}
		catch(SQLException e){
			log.error("Error to close connection: "+e.getMessage());
		}
		
	}
	
	private RequestDispatcher setResponseController(String view){
		String url=this.rutaJSP+view+".jsp";
		return getServletContext().getRequestDispatcher(url);
	}

}
