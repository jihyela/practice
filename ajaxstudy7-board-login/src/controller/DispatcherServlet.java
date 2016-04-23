package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DispatcherServlet
 */
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DispatcherServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	request.setCharacterEncoding("utf-8");
    	doGet(request,response);
    }
    @Override
    protected void doGet(HttpServletRequest request, 
    		HttpServletResponse response)
    		throws ServletException, IOException {
    	String command=request.getParameter("command");    	
    	Controller controller=HandlerMapping.getInstance().create(command);  	 
		try {
			ModelAndView mv= controller.execute(request, response);
			String url=mv.getViewName();
			if(url.startsWith("redirect:"))
				response.sendRedirect(url.replace("redirect:", ""));
			else{	
				HashMap<String,Object> map=mv.getMap();
				if(map.isEmpty()==false){
					Iterator<String> it=map.keySet().iterator();
					while(it.hasNext()){
						String key=it.next();
						request.setAttribute(key, map.get(key));
					}
				}
				request.getRequestDispatcher(url).forward(request,response);
			}
		} catch (Exception e) {			
			e.printStackTrace();
			//response.sendRedirect("error.jsp");
		}    	
    }
}





