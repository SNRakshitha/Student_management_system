package controller;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Registration;
@WebServlet(name = "search", urlPatterns = {"/search"})
public class search extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        Registration u = new Registration(session);
        try {
            if(session.getAttribute("id") != null && session.getAttribute("id").equals("1")){
            String id = request.getParameter("id");
            //request.getRequestDispatcher("search.jsp?id=" + id).forward(request, response);
	response.sendRedirect("search.jsp?id="+id);            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}
