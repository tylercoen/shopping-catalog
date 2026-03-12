package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DatabaseManager;
import model.User;
import model.UserManager;

@WebServlet("/access")

public class AccessServlet extends HttpServlet {

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

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
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
				response.sendRedirect("/shopping-catalog/login.html");
				return;
			}

			if (sessionEmail.equals(email)) {
				response.sendRedirect("/shopping-catalog/catalog.html");
				return;
			} else {
				session.invalidate();
				response.sendRedirect("/shopping-catalog/login.html");
				return;
			}

		}

		// attempt login
		try {
			User userToLogin = new User(email, password);

			User loggedInUser = userManager.loginUser(userToLogin);

			HttpSession newSession = request.getSession(true);
			newSession.setAttribute(email, loggedInUser.getEmail());

			response.sendRedirect("/shopping-catalog/catalog.html");
		} catch (Exception e) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
		}

	}

	public void registerAction(HttpServletRequest request, HttpServletResponse response) throws IOException {

		// 1. read parameters from request
		String email = request.getParameter("email");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String password = request.getParameter("password");

		// 2. check if a session already exists but don't create one
		HttpSession session = request.getSession(false);

		if (session != null) {
			session.invalidate();
			response.sendError(HttpServletResponse.SC_BAD_REQUEST,
					"Session already exists. Please try registering again.");
			return;
		}

		try {

			// 3. create new user
			User registeringUser = new User(firstName, lastName, email, password);

			// 4. register user
			userManager.registerUser(registeringUser);

			// 5. redirect to login page
			response.sendRedirect("/shopping-catalog/login.html");
		} catch (Exception e) {
			// 6. send error if registration fails
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
		}

	}

	public void logoutAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// get session but don't create one
		HttpSession session = request.getSession(false);

		// if session does not exist, redirect to login
		if (session == null) {
			response.sendRedirect("/shopping-catalog/login.html");
			return;
		}

		// if session exists, invalidate it
		session.invalidate();

		// redirect user to login
		response.sendRedirect("/shopping-catalog/login.html");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");

		if (action == null) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Action paramter is required");
			return;
		}

		switch (action) {
		case "login":
			loginAction(request, response);
			break;
		case "register":
			registerAction(request, response);
			break;
		case "logout":
			logoutAction(request, response);
			break;
		default:
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
		}
	}

}
