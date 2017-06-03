package org.example;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;



/**
 * Servlet implementation class Servlet
 */
public class Servlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final Logger log = LogManager.getLogger("Servlet: ");
	
	private String rutaJSP;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		this.rutaJSP=config.getInitParameter("rutaJSP");
		BasicConfigurator.configure();
		log.info("ruta jsp: "+this.rutaJSP);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String action=request.getParameter("action");
		
		if(action!=null){
			
			if(action.equals("index")){
				this.setResponseController("index").forward(request, response);				
			}
			else if(action.equals("login")){
				this.setResponseController("login").forward(request, response);
			}
			
			
		}
		else{
			this.setResponseController("index").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
String action = request.getParameter("action");
		
		if (action != null) {
			
			if (action.equals("startSession")) {
				
				String user = request.getParameter("user");
				String password = request.getParameter("password");
				
				// Ámbito Request
				request.setAttribute("user", user);
				request.setAttribute("password", password);
				
				// Ámbito Sesión
				HttpSession sesion = request.getSession();
				sesion.setAttribute("user", user);
				sesion.setAttribute("password", password);
				
				// Ámbito Contexto
				ServletContext contexto = getServletContext();
				contexto.setAttribute("user", user);
				contexto.setAttribute("password", password);
				
				this.setResponseController("postLogin").forward(request, response);
				
			}
			
			
		}
		else {
			getServletContext().getRequestDispatcher(rutaJSP+"index.jsp").forward(request, response);
		}
		
	}
	
	private RequestDispatcher setResponseController(String view){
		String url=this.rutaJSP+view+".jsp";
		return getServletContext().getRequestDispatcher(url);
	}

}
