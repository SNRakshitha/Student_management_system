package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import forgotpass.SendEmail;
import forgotpass.User;

@WebServlet(name = "ForgotPassword ", urlPatterns = {"/forgot"}) 
public class ForgotPassword extends HttpServlet {

 @Override
 protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

  String username = req.getParameter("username");
  String useremail = req.getParameter("useremail");
  HttpSession session = req.getSession();

  SendEmail s = new SendEmail();
  String code = s.getRandom();

  User user = new User(username, useremail, code);

  boolean check = s.sendEmail(user);
  if (check) {
   session.setAttribute("useremail", user.getUseremail());
   session.setAttribute("otp", user.getUsercode());
   resp.sendRedirect("Verify.jsp");
  } else {
   System.out.println("Otp failed!!!");
  }
 }

}