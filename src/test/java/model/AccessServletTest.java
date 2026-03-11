package model;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

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

	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
