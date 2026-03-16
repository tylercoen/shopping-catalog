package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CartItem;
import model.CartManager;
import model.CartSummaryHtmlGenerator;
import model.DatabaseManager;

@WebServlet("/cart")

public class CartServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private CartManager cartManager;
	private DatabaseManager db;

	@Override
	public void init() throws ServletException {
		db = new DatabaseManager();

		try {
			cartManager = new CartManager(db.getUserCarts());
		} catch (Exception e) {
			throw new ServletException("failed to initialize CartManager", e);
		}
	}

	@Override
	public void destroy() {
		try {
			db.writeUserCarts(cartManager.getUserCarts());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Checking cart session...");
		HttpSession session = request.getSession(false);
		System.out.println("Session: " + session);
		System.out.println("Email attribute: " + session.getAttribute("email"));

		if (session == null) {
			response.sendRedirect("/shopping-catalog/login.html");
			return;
		}

		String email = (String) session.getAttribute("email");
		if (email == null) {
			session.invalidate();
			response.sendRedirect("/shopping-catalog/login.html");
			return;
		}

		Map<CartItem, Integer> cart = cartManager.getUserCart(email);

		PrintWriter out = response.getWriter();
		response.setContentType("text/html");

		if (cart == null || cart.isEmpty()) {
			out.println("<html>");
			out.println("<body>");
			out.println("<h2>Your cart is empty</h2>");
			out.println("<a href=\"/shopping-catalog/catalog.html\">Return to Catalog</a>");
			out.println("</body>");
			out.println("</html>");
		} else {
			String html = CartSummaryHtmlGenerator.getCartSummaryPage(cart);
			out.println(html);
		}

	}
}
