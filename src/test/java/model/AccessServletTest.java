package model;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;

import servlet.AccessServlet;

public class AccessServletTest {
	private AccessServlet servlet;
	private HttpServletRequest request;
	private HttpServletResponse response;

	@Before
	public void setup() {
		servlet = new AccessServlet();
		request = mock(HttpServletRequest.class);
		response = mock(HttpServletResponse.class);
		servlet.setUserManager(new UserManager());

	}

	@Test
	public void testRegisterActionSuccess() throws Exception {
		when(request.getParameter("email")).thenReturn("test@email.com");
		when(request.getParameter("firstName")).thenReturn("Test");
		when(request.getParameter("lastName")).thenReturn("User");
		when(request.getParameter("password")).thenReturn("password123");

		when(request.getSession(false)).thenReturn(null);

		servlet.registerAction(request, response);

		verify(response).sendRedirect("/shopping-catalog/login.html");
	}

	@Test
	public void testLogouActionNoSession() throws Exception {
		when(request.getSession(false)).thenReturn(null);

		servlet.logoutAction(request, response);

		verify(response).sendRedirect("/shopping-catalog/login.html");
	}

}
