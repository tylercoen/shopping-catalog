package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DatabaseManager;
import model.User;
import model.UserManager;

public class AccessServlet {

	private UserManager userManager;
	private DatabaseManager db;
	private static final long serialVersionUID = 1L;

	public void init() throws ServletException {
		db = new DatabaseManager();
		try {
			userManager = new UserManager(db.getUsers());
		} catch (Exception e) {
			throw new ServletException("Failed to initialize UserManager", e);
		}
	}

	public void destroy() {
		try {
			db.writeUsers(userManager.getUsers());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void loginAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		// retrieve but don't create session
		HttpSession session = request.getSession(false);
		if (session != null) {
			String sessionEmail = (String) session.getAttribute("email");
			if (sessionEmail == null) {
				session.invalidate();
				response.sendRedirect("/catalog/login.html");
				return;
			}

			if (sessionEmail.equals(email)) {
				response.sendRedirect("/catalog/catalog.html");
				return;
			} else {
				session.invalidate();
				response.sendRedirect("/catalog/login.html");
				return;
			}

		}

		// attempt login
		try {
			User userToLogin = new User(email, password);

			User loggedInUser = userManager.loginUser(userToLogin);

			HttpSession newSession = request.getSession(true);
			newSession.setAttribute(email, loggedInUser.getEmail());

			response.sendRedirect("/catalog/catalog.html");
		} catch (Exception e) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
		}

	}

}
