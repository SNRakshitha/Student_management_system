package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/verify")
public class Verify extends HttpServlet{
 @Override
 protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
  String usercode =req.getParameter("usercode");
  HttpSession session = req.getSession();

  if(usercode.equals(session.getAttribute("otp"))) {
   req.setAttribute("status", "OTP is Successfully Verified !");
   RequestDispatcher rd1 = req.getRequestDispatcher("ResetPass.jsp");
            rd1.forward(req, resp);
  }else {
   req.setAttribute("status", "Wrong OTP!!");
   RequestDispatcher rd1 = req.getRequestDispatcher("Verify.jsp");
            rd1.forward(req, resp);
   
  }
 }
}
