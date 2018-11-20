package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bean.LoginBean;
import service.LoginService;

@SuppressWarnings("serial")
public class ControllerServlet extends HttpServlet{
	
	Logger logger = LoggerFactory.getLogger(ControllerServlet.class);
	
	LoginService loginService = new LoginService();
	
	@Override
	public void init() throws ServletException {
		super.init();
		logger.info("Loading dummy data...");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String password = req.getParameter("password");
		
		LoginBean loginBean = new LoginBean(name, password);
		req.setAttribute("login-bean", loginBean);
		
		if(loginService.authorize(loginBean)) {
			RequestDispatcher rd = req.getRequestDispatcher("login-success.jsp");
			rd.forward(req, resp);
		} else {
			RequestDispatcher rd = req.getRequestDispatcher("login-error.jsp");
			rd.forward(req, resp);
		}
	}
	
	

}
