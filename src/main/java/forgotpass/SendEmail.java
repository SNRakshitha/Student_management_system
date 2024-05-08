package forgotpass;

import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;

import javax.mail.internet.MimeMessage;
	public class SendEmail {	
	 public String getRandom() {
	  Random rd = new Random();
	  int otp = rd.nextInt(999999);
	  return String.format("%06d", otp);
	 }

	public boolean sendEmail(User user) {
	 boolean check = false;
	 String toEmail = user.getUseremail();
	 String fromEmail = "rakshitha242401@gmail.com";
	 String pass = "joww wxbb azgm izgl";
	 try {
	  Properties prop = new Properties();
	  prop.put("mail.smtp.host", "smtp.gmail.com");
	  prop.put("mail.smtp.starttls.enable", "true");
	  prop.put("mail.smtp.port", 587);
	  prop.put("mail.smtp.auth", "true");
	  // Mail session
	 Session session = Session.getInstance(prop, new Authenticator() {
	 @Override
	 protected PasswordAuthentication getPasswordAuthentication() {
	  return new PasswordAuthentication(fromEmail, pass);
	  }
	 });
	 Message msg = new MimeMessage(session);
	 msg.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
	 msg.setFrom(new InternetAddress(fromEmail));
	 msg.setSubject("Password Reset ");
	 msg.setText("Your OTP to reset the Password is " +user.getUsercode());
	 // send email
	 Transport.send(msg);
	 check = true;
	 } catch (Exception e) {
	  e.printStackTrace();
	 }
	 return check;
	 }
	}


