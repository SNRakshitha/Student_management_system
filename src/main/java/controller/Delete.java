package controller;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Registration;
@WebServlet(name = "Delete", urlPatterns = {"/delete"}) 
public class Delete extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try { 
			HttpSession session=request.getSession(); 
			Registration reg=new Registration(session); 
			int id=Integer.parseInt(request.getParameter("userid"));  
			String status=reg.delete(id); 
			if(status.equals("success")) { 
			request.setAttribute("status", "Account Deleted Sucessfully"); 
			RequestDispatcher re=request.getRequestDispatcher("Delete.jsp"); 
			re.forward(request, response); 
			}else { 
			request.setAttribute("status", "Failure Occured"); 
			RequestDispatcher re=request.getRequestDispatcher("Delete.jsp"); 
			re.forward(request, response); 
			} 
			}catch(Exception e) { 
			e.printStackTrace(); 
			} 		
	}
}
