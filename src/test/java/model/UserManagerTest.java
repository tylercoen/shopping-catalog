package model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class UserManagerTest {

	@Test
	public void testSuccessfulLogin() {
		UserManager manager = new UserManager();

		User user = new User("John", "Doe", "john@email.com", "password123");

		manager.registerUser(user);

		User loginAttempt = new User("john@email.com", "password123");

		User loggedIn = manager.loginUser(loginAttempt);

		assertEquals("john@email.com", loggedIn.getEmail());

	}

	@Test(expected = IllegalStateException.class)
	public void testInvalidPassword() {
		UserManager manager = new UserManager();

		User user = new User("John", "Doe", "john@email.com", "password123");

		manager.registerUser(user);

		User loginAttempt = new User("john@email.com", "wrongpass");

		manager.loginUser(loginAttempt);
	}

}
