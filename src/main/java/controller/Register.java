package controller;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Registration;
@WebServlet(name = "Register", urlPatterns = {"/register"}) 
public class Register extends HttpServlet {	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8"); 
        // type of the response sent to the client or browser 
        PrintWriter out = response.getWriter(); 
        HttpSession session = request.getSession(); 
        Registration reg = new Registration(session); 
        try { 
            if (request.getParameter("register") != null) { 
                String name = request.getParameter("name"); 
                String phone = request.getParameter("phone"); 
                String email = request.getParameter("email"); 
                String pw = request.getParameter("pw"); 
                String cp = request.getParameter("cp");  
                if (pw.equals(cp)) { 
                    String status = reg.Registration(name, phone, email, pw);                                        
                    if (status.equals("existed")) {  
                        request.setAttribute("status", "Existed record"); 
                        RequestDispatcher rd1 = request.getRequestDispatcher("Registration.jsp"); 
                        rd1.forward(request, response); 
                    } else if (status.equals("success")) { 
                        request.setAttribute("status", "Successfully Registered"); 
                        RequestDispatcher rd1 = request.getRequestDispatcher("Login.jsp"); 
                        rd1.forward(request, response); 
                    } else if (status.equals("failure")) { 
                        request.setAttribute("status", "Registration failed"); 
                        RequestDispatcher rd1 = request.getRequestDispatcher("Registration.jsp"); 
                        rd1.forward(request, response); 
                    } 
                } 
            } else if (request.getParameter("login") != null) { 
                String email = request.getParameter("email"); 
                String pass = request.getParameter("pw"); 
                String status = reg.login(email, pass); 
                if (status.equals("success")) { 
                    RequestDispatcher rd1 = request.getRequestDispatcher("index.jsp"); 
                    rd1.forward(request, response);  
                } else if (status.equals("failure")) { 
                    request.setAttribute("status", "Login failed"); 
                    RequestDispatcher rd1 = request.getRequestDispatcher("Login.jsp"); 
                    rd1.forward(request, response); 
                } 
            } else if (session.getAttribute("uname") != null && request.getParameter("submit") != null) {
                String name = request.getParameter("name");
                String pno = request.getParameter("pno");
                String email = request.getParameter("email");
                Registration u = new Registration(session);
                String status = u.update(name, pno, email);
                if (status.equals("success")) {
                    request.setAttribute("status", "Profile successfully Updated");
                    RequestDispatcher rd1 = request.getRequestDispatcher("index.jsp");
                    rd1.forward(request, response);
                } else {
                    request.setAttribute("status", "Updation failure");
                    RequestDispatcher rd1 = request.getRequestDispatcher("index.jsp");
                    rd1.forward(request, response);
                }
            }
            else if (request.getParameter("logout") != null) { 
                session.invalidate(); 
                RequestDispatcher rd1 = request.getRequestDispatcher("index.jsp"); 
                rd1.forward(request, response); 
            } 
            else if (request.getParameter("reset") != null) {
            	 String email = (String) session.getAttribute("useremail");
            	 String newPass = request.getParameter("newPW");
            	 String confirmPass = request.getParameter("confirmPW");
            	 if(newPass.equals(confirmPass)) {
            	 boolean check=reg.updatePassword(email, confirmPass);
            	 if(check) {
            	 System.out.println("Password Update successfully!!");
            	 request.setAttribute("status", "Password Update successfully!!");
            	 RequestDispatcher rd1 = request.getRequestDispatcher("Login.jsp");
            	 rd1.forward(request, response);
            	 }else {
            	 System.out.println("Password Update failed");
            	 request.setAttribute("status", "Password Update failed !!");
            	RequestDispatcher rd1 = request.getRequestDispatcher("ResetPass.jsp");
            	 rd1.forward(request, response);
            	  }
            	 }
            	 }
        } catch (Exception e) { 
            e.printStackTrace(); 
        } 
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req,resp);
	}

}
