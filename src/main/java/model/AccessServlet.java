package model;

import javax.servlet.ServletException;

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
			db.writeUserCarts(userManager.getUsers());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
